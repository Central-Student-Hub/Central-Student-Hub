package com.centralstudenthub.service;

import com.centralstudenthub.Model.Response.student_profile.course.CourseResponse;
import com.centralstudenthub.entity.student_profile.course.Course;
import com.centralstudenthub.entity.student_profile.course.course_prerequisites.CoursePrerequisite;
import com.centralstudenthub.entity.student_profile.course.course_prerequisites.CoursePrerequisiteId;
import com.centralstudenthub.exception.AlreadyExistsException;
import com.centralstudenthub.exception.DatabaseLogicalConstraintException;
import com.centralstudenthub.exception.NotFoundException;
import com.centralstudenthub.repository.CourseRepository;
import com.centralstudenthub.repository.CoursePrerequisiteRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CoursePrerequisiteServiceTest {
    @Autowired
    private CoursePrerequisiteService coursePrerequisiteService;

    @MockBean
    private CourseRepository courseRepository;

    @MockBean
    private CoursePrerequisiteRepository coursePrerequisiteRepository;

    @Test
    public void addCoursePrerequisite_courseId_prerequisiteId_InDB_coursePrerequisiteId_NotInDB() throws
            AlreadyExistsException, NotFoundException, DatabaseLogicalConstraintException {
        Course course = Course.builder().courseId(1).build();
        Course prerequisite = Course.builder().courseId(2).build();

        Mockito.when(courseRepository.findById(course.getCourseId())).thenReturn(Optional.of(course));
        Mockito.when(courseRepository.findById(prerequisite.getCourseId())).thenReturn(Optional.of(prerequisite));
        Mockito.when(coursePrerequisiteRepository.existsById(Mockito.any(CoursePrerequisiteId.class))).thenReturn(false);
        Mockito.when(coursePrerequisiteRepository.save(Mockito.any(CoursePrerequisite.class))).thenReturn(null);

        assertTrue(coursePrerequisiteService.addCoursePrerequisite(course.getCourseId(), prerequisite.getCourseId()));
    }

    @Test
    public void addCoursePrerequisite_courseId_OR_prerequisiteId_NotInDB() {
        Mockito.when(courseRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> coursePrerequisiteService.addCoursePrerequisite(1, 2));
    }

    @Test
    public void addCoursePrerequisite_courseId_prerequisiteId_coursePrerequisiteId_InDB() {
        Course course = Course.builder().courseId(1).build();
        Course prerequisite = Course.builder().courseId(2).build();

        Mockito.when(courseRepository.findById(course.getCourseId())).thenReturn(Optional.of(course));
        Mockito.when(courseRepository.findById(prerequisite.getCourseId())).thenReturn(Optional.of(prerequisite));
        Mockito.when(coursePrerequisiteRepository.existsById(Mockito.any(CoursePrerequisiteId.class))).thenReturn(true);
        assertThrows(AlreadyExistsException.class, () -> coursePrerequisiteService.addCoursePrerequisite(course.getCourseId(), prerequisite.getCourseId()));
    }

    @Test
    public void addCoursePrerequisite_courseId_Equals_prerequisiteId() {
        Course course = Course.builder().courseId(1).build();
        Course prerequisite = Course.builder().courseId(1).build();

        Mockito.when(courseRepository.findById(course.getCourseId())).thenReturn(Optional.of(course));
        Mockito.when(courseRepository.findById(prerequisite.getCourseId())).thenReturn(Optional.of(prerequisite));
        assertThrows(DatabaseLogicalConstraintException.class, () -> coursePrerequisiteService.addCoursePrerequisite(course.getCourseId(), prerequisite.getCourseId()));
    }

    @Test
    public void getCoursePrerequisites_courseId_NotInDB() {
        Mockito.when(courseRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> coursePrerequisiteService.getCoursePrerequisites(1));
    }

    @Test
    public void getCoursePrerequisite_courseId_NotInDB() {
        Mockito.when(courseRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> coursePrerequisiteService.getCoursePrerequisites(1));
    }

    @Test
    public void getCoursePrerequisites_courseId_InDB() throws NotFoundException {
        Course course = Course.builder().courseId(0).build();
        List<Integer> coursePrerequisitesIds = new ArrayList<>();
        List<CourseResponse> coursePrerequisites = new ArrayList<>();
        for (int i = 1; i <= 1000; i++) {
            coursePrerequisitesIds.add(i);
            coursePrerequisites.add(
                CourseResponse.builder()
                    .courseId(i)
                    .code("SWE " + i)
                    .name("Software Engineering " + i)
                    .description("Software Engineering Course " + i)
                    .creditHours(3)
                    .build()
            );
        }

        Mockito.when(courseRepository.findById(course.getCourseId())).thenReturn(Optional.of(course));
        Mockito.when(coursePrerequisiteRepository.findAllPrerequisitesByCourseId(course.getCourseId())).thenReturn(coursePrerequisitesIds);
        for (int i = 0; i < coursePrerequisitesIds.size(); i++)
            Mockito.when(courseRepository.findById(coursePrerequisitesIds.get(i))).thenReturn(Optional.of(coursePrerequisites.get(i).toEntity()));

        assertEquals(coursePrerequisites, coursePrerequisiteService.getCoursePrerequisites(course.getCourseId()));
    }

    @Test
    public void deleteCoursePrerequisite_courseId_OR_prerequisiteId_NotInDB() {
        Mockito.when(courseRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> coursePrerequisiteService.deleteCoursePrerequisite(1, 2));
    }

    @Test
    public void deleteCoursePrerequisite_courseId_prerequisiteId_InDB_coursePrerequisiteId_NotInDB() {
        Course course = Course.builder().courseId(1).build();
        Course prerequisite = Course.builder().courseId(2).build();

        Mockito.when(courseRepository.findById(course.getCourseId())).thenReturn(Optional.of(course));
        Mockito.when(courseRepository.findById(prerequisite.getCourseId())).thenReturn(Optional.of(prerequisite));
        Mockito.when(coursePrerequisiteRepository.findById(Mockito.any(CoursePrerequisiteId.class))).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> coursePrerequisiteService.deleteCoursePrerequisite(course.getCourseId(), prerequisite.getCourseId()));
    }

    @Test
    public void deleteCoursePrerequisite_courseId_prerequisiteId_coursePrerequisiteId_InDB () throws NotFoundException {
        Course course = Course.builder().courseId(1).build();
        Course prerequisite = Course.builder().courseId(2).build();
        CoursePrerequisiteId coursePrerequisiteId = CoursePrerequisiteId.builder().course(course).prerequisite(prerequisite).build();
        CoursePrerequisite coursePrerequisite = CoursePrerequisite.builder().id(coursePrerequisiteId).build();

        Mockito.when(courseRepository.findById(course.getCourseId())).thenReturn(Optional.of(course));
        Mockito.when(courseRepository.findById(prerequisite.getCourseId())).thenReturn(Optional.of(prerequisite));
        Mockito.when(coursePrerequisiteRepository.findById(Mockito.any(CoursePrerequisiteId.class))).thenReturn(Optional.of(coursePrerequisite));

        assertTrue(coursePrerequisiteService.deleteCoursePrerequisite(course.getCourseId(), prerequisite.getCourseId()));
    }

}