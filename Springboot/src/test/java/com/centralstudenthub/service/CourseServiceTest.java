package com.centralstudenthub.service;

import com.centralstudenthub.Model.Request.CourseRequest;
import com.centralstudenthub.entity.student_profile.course.Course;
import com.centralstudenthub.exception.NotFoundException;
import com.centralstudenthub.repository.CourseRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
class CourseServiceTest {
    @Autowired
    private CourseService courseService;

    @MockBean
    private CourseRepository courseRepository;

    @Test
    public void saveCourseWith_Distinct_NotNull_ValueCode() {
        CourseRequest course = CourseRequest.builder()
                .code("SWE 401")
                .name("Software Engineering")
                .description("Software Engineering Course")
                .creditHours(3)
                .build();

        Course savedCourse = course.toEntity();
        Mockito.when(courseRepository.existsByCode(course.getCode())).thenReturn(false);
        Mockito.when(courseRepository.save(Mockito.any(Course.class))).thenReturn(savedCourse);

        Integer savedCourseId = courseService.addCourse(course);
        assertEquals(savedCourse.getCourseId(), savedCourseId);
    }

    @Test
    public void saveCourseWith_NotDistinct_NotNull_ValueCode() {
        CourseRequest course = CourseRequest.builder()
                .code("SWE 401")
                .name("Software Engineering")
                .description("Software Engineering Course")
                .creditHours(3)
                .build();

        Mockito.when(courseRepository.existsByCode(course.getCode())).thenReturn(true);
        assertEquals(-1, courseService.addCourse(course));
    }

    @Test
    public void saveCoursesThatContains_At_Least_A_Course_With_Distinct_NotNull_ValueCode() {
        CourseRequest[] courses = new CourseRequest[1000];
        Random random = new Random();
        for(int i = 0; i < 999; i++) {
            courses[i] = CourseRequest.builder()
                    .code("SWE " + random.nextInt(1000))
                    .name("Software Engineering")
                    .description("Software Engineering Course")
                    .creditHours(3)
                    .build();
        }
        courses[999] = CourseRequest.builder()
                .code("SWE 1001")
                .name("Software Engineering")
                .description("Software Engineering Course")
                .creditHours(3)
                .build();

        Course[] savedCourses = new Course[1000];
        Map<String, Integer> coursesCodesCnt = new HashMap<>();
        for(int i = 0; i < 1000; i++) {
            savedCourses[i] = courses[i].toEntity();
            coursesCodesCnt.put(courses[i].getCode(), coursesCodesCnt.getOrDefault(courses[i].getCode(), 0) + 1);
        }

        for (Course course : savedCourses) {
            if (coursesCodesCnt.get(course.getCode()) == 1)
                Mockito.when(courseRepository.existsByCode(course.getCode())).thenReturn(false);
            else
                Mockito.when(courseRepository.existsByCode(course.getCode())).thenReturn(true);
        }

        Mockito.when(courseRepository.saveAll(Mockito.any())).thenReturn(List.of(savedCourses));
        assertTrue(courseService.addCourses(courses));
    }

    @Test
    public void saveCoursesThatContains_All_Courses_With_Same_Code() {
        CourseRequest[] courses = new CourseRequest[1000];
        for(int i = 0; i < 1000; i++) {
            courses[i] = CourseRequest.builder()
                    .code("SWE 401")
                    .name("Software Engineering")
                    .description("Software Engineering Course")
                    .creditHours(3)
                    .build();
        }

        Course[] savedCourses = new Course[1000];
        Map<String, Integer> coursesCodesCnt = new HashMap<>();
        for(int i = 0; i < 1000; i++) {
            savedCourses[i] = courses[i].toEntity();
            coursesCodesCnt.put(courses[i].getCode(), coursesCodesCnt.getOrDefault(courses[i].getCode(), 0) + 1);
        }

        for (Course course : savedCourses) {
            if (coursesCodesCnt.get(course.getCode()) == 1)
                Mockito.when(courseRepository.existsByCode(course.getCode())).thenReturn(false);
            else
                Mockito.when(courseRepository.existsByCode(course.getCode())).thenReturn(true);
        }

        Mockito.when(courseRepository.saveAll(Mockito.any())).thenReturn(List.of(savedCourses));
        assertFalse(courseService.addCourses(courses));
    }

    @Test
    public void getCourseThatExistsInTheDB() throws NotFoundException {
        Course course = Course.builder()
                .courseId(1)
                .code("SWE 401")
                .name("Software Engineering")
                .description("Software Engineering Course")
                .creditHours(3)
                .build();

        Mockito.when(courseRepository.findById(course.getCourseId())).thenReturn(Optional.of(course));
        assertEquals(course.toResponse(), courseService.getCourse(course.getCourseId()));
    }

    @Test
    public void getCourseThatDoesNotExistInTheDB() {
        Mockito.when(courseRepository.findById(1)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> courseService.getCourse(1));
    }

    @Test
    public void updateCourseThatDoesNotExistInTheDB() {
        CourseRequest courseUpdates = CourseRequest.builder()
                .code("SWE 401")
                .name("Software Engineering")
                .description("Software Engineering Course")
                .creditHours(3)
                .build();

        Mockito.when(courseRepository.findById(1)).thenReturn(Optional.empty());
        assertFalse(courseService.updateCourse(1, courseUpdates));
    }

    @Test
    public void updateCourseThatExistsInTheDB() {
        Course course = Course.builder()
                .courseId(1)
                .code("SWE 401")
                .name("Software Engineering")
                .description("Software Engineering Course")
                .creditHours(3)
                .build();

        CourseRequest courseUpdates = CourseRequest.builder()
                .code("SWE 401")
                .name("Software Engineering")
                .description("Software Engineering Course")
                .creditHours(3)
                .build();

        Mockito.when(courseRepository.findById(1)).thenReturn(Optional.of(course));
        Mockito.when(courseRepository.save(any(Course.class))).thenReturn(course);
        assertTrue(courseService.updateCourse(1, courseUpdates));
    }

    @Test
    public void deleteCourseThatDoesNotExistInTheDB() {
        Mockito.when(courseRepository.findById(1)).thenReturn(Optional.empty());
        assertFalse(courseService.deleteCourse(1));
    }

    @Test
    public void deleteCourseThatExistsInTheDB() {
        Course course = Course.builder()
                .courseId(1)
                .code("SWE 401")
                .name("Software Engineering")
                .description("Software Engineering Course")
                .creditHours(3)
                .build();

        Mockito.when(courseRepository.findById(1)).thenReturn(Optional.of(course));
        Mockito.doNothing().when(courseRepository).delete(course);
        assertTrue(courseService.deleteCourse(1));
    }

}