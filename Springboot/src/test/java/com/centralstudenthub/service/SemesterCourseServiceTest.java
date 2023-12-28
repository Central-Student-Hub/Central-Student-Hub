package com.centralstudenthub.service;

import com.centralstudenthub.Model.Request.SemesterCourseRequest;
import com.centralstudenthub.Model.Semester;
import com.centralstudenthub.entity.student_profile.course.Course;
import com.centralstudenthub.entity.student_profile.course.semester_courses.SemesterCourse;
import com.centralstudenthub.exception.NotFoundException;
import com.centralstudenthub.repository.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SemesterCourseServiceTest {
    @Autowired
    private SemesterCourseService semesterCourseService;

    @MockBean
    private CourseRepository courseRepository;

    @MockBean
    private SemesterCourseRepository semesterCourseRepository;

    @Test
    public void addSemesterCourseTest() throws NotFoundException {
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int coin = random.nextInt(2);
            if (coin == 0) {
                Mockito.when(courseRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());
                assertThrows(NotFoundException.class, () -> semesterCourseService.addSemesterCourse(
                        SemesterCourseRequest.builder()
                                .courseId(random.nextInt())
                                .maxSeats(random.nextInt())
                                .semester(Semester.SPRING)
                                .build()
                        )
                );
            } else {
                SemesterCourseRequest semesterCourseRequest = SemesterCourseRequest.builder().courseId(random.nextInt())
                        .maxSeats(random.nextInt()).semester(Semester.SPRING).build();
                Course course = Course.builder().courseId(semesterCourseRequest.getCourseId()).build();
                SemesterCourse semesterCourse = SemesterCourse.builder().semCourseId(random.nextLong()).course(course).semester(semesterCourseRequest.getSemester())
                        .maxSeats(semesterCourseRequest.getMaxSeats()).build();
                Mockito.when(semesterCourseRepository.save(Mockito.any(SemesterCourse.class))).then(
                        invocationOnMock -> {
                            SemesterCourse semesterCourse1 = invocationOnMock.getArgument(0);
                            semesterCourse1.setSemCourseId(semesterCourse.getSemCourseId());
                            return semesterCourse1;
                        }
                );
                Mockito.when(courseRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(course));
                assertEquals(semesterCourse.getSemCourseId(), semesterCourseService.addSemesterCourse(semesterCourseRequest));
            }
        }
    }
}