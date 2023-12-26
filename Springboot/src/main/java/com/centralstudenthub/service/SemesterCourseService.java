package com.centralstudenthub.service;

import com.centralstudenthub.Model.Request.SemesterCourseRequest;
import com.centralstudenthub.Model.Response.SemesterCourseResponse;
import com.centralstudenthub.Model.Semester;
import com.centralstudenthub.entity.student_profile.course.Course;
import com.centralstudenthub.entity.student_profile.course.semester_courses.SemesterCourse;
import com.centralstudenthub.exception.NotFoundException;
import com.centralstudenthub.repository.CourseRepository;
import com.centralstudenthub.repository.SemesterCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SemesterCourseService {
    private final CourseRepository courseRepository;
    private final SemesterCourseRepository semesterCourseRepository;

    @Autowired
    public SemesterCourseService(CourseRepository courseRepository, SemesterCourseRepository semesterCourseRepository) {
        this.semesterCourseRepository = semesterCourseRepository;
        this.courseRepository = courseRepository;
    }

    public Long addSemesterCourse(SemesterCourseRequest semesterCourseRequest) throws NotFoundException {
        Optional<Course> course = courseRepository.findById(semesterCourseRequest.getCourseId());
        if (course.isEmpty())
            throw new NotFoundException("Course not found");

        SemesterCourse semesterCourse = SemesterCourse.builder().course(course.get())
                .semester(semesterCourseRequest.getSemester())
                .maxSeats(semesterCourseRequest.getMaxSeats())
                .build();
        semesterCourseRepository.save(semesterCourse);
        return semesterCourse.getSemCourseId();
    }

    public SemesterCourseResponse getSemesterCourse(Long id) throws NotFoundException {
        Optional<SemesterCourse> semesterCourse = semesterCourseRepository.findById(id);
        if (semesterCourse.isEmpty())
            throw new NotFoundException("Semester course not found");

        return semesterCourse.get().toResponse();

    }

    public List<SemesterCourseResponse> getSemesterCourses(Semester semester) throws NotFoundException {
        List<SemesterCourse> semesterCourses = semesterCourseRepository.findBySemester(semester);
        if (semesterCourses.isEmpty())
            throw new NotFoundException("Semester courses not found");

        return semesterCourses.stream().map(SemesterCourse::toResponse).collect(Collectors.toList());
    }

    public boolean updateSemesterCourse(Long id, SemesterCourseRequest semesterCourseUpdates) throws NotFoundException {
        Optional<SemesterCourse> semesterCourse = semesterCourseRepository.findById(id);
        if (semesterCourse.isEmpty())
            throw new NotFoundException("Semester course not found");

        semesterCourse.get().setSemester(semesterCourseUpdates.getSemester());
        semesterCourse.get().setMaxSeats(semesterCourseUpdates.getMaxSeats());
        semesterCourseRepository.save(semesterCourse.get());
        return true;
    }

    public boolean deleteSemesterCourse(Long id) throws NotFoundException {
        Optional<SemesterCourse> semesterCourse = semesterCourseRepository.findById(id);
        if (semesterCourse.isEmpty())
            throw new NotFoundException("Semester course not found");

        semesterCourseRepository.delete(semesterCourse.get());
        return true;
    }
}
