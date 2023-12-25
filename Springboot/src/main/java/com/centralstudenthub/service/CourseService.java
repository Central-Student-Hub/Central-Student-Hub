package com.centralstudenthub.service;

import com.centralstudenthub.Model.Request.CourseRequest;
import com.centralstudenthub.Model.Response.CourseResponse;
import com.centralstudenthub.entity.student_profile.course.Course;
import com.centralstudenthub.exception.AlreadyExistsException;
import com.centralstudenthub.exception.NotFoundException;
import com.centralstudenthub.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Integer addCourse(CourseRequest course) throws AlreadyExistsException {
        if(courseRepository.existsByCode(course.getCode()))
            throw new AlreadyExistsException("Course already exists...");

        Course savedCourse = Course.builder()
                .code(course.getCode())
                .name(course.getName())
                .description(course.getDescription())
                .creditHours(course.getCreditHours())
                .build();
        courseRepository.save(savedCourse);
        return savedCourse.toResponse().getCourseId();
    }

    public List<CourseResponse> addCourses(CourseRequest[] courses) throws AlreadyExistsException {
        int addedCoursesCount = 0;
        List<CourseResponse> savedCourses = new ArrayList<>();
        for(CourseRequest course: courses) {
            if(courseRepository.existsByCode(course.getCode())) continue;

            Course savedCourse = Course.builder()
                    .code(course.getCode())
                    .name(course.getName())
                    .description(course.getDescription())
                    .creditHours(course.getCreditHours())
                    .build();
            courseRepository.save(savedCourse);
            savedCourses.add(savedCourse.toResponse());
            addedCoursesCount++;
        }
        if (addedCoursesCount == 0)
            throw new AlreadyExistsException("All courses already exists...");
        return savedCourses;
    }

    public CourseResponse getCourse(Integer id) throws NotFoundException {
        Optional<Course> courseOptional = courseRepository.findById(id);
        if(courseOptional.isEmpty())
            throw new NotFoundException("Course not found...");

        return courseOptional.get().toResponse();
    }

    public List<CourseResponse> getAllCourses() {
        List<Course> courses = courseRepository.findAll();
        List<CourseResponse> courseResponses = new ArrayList<>();
        for(Course course: courses)
            courseResponses.add(course.toResponse());
        return courseResponses;
    }

    public CourseResponse updateCourse(Integer id, CourseRequest courseUpdates) throws NotFoundException {
        Optional<Course> courseOptional = courseRepository.findById(id);
        if(courseOptional.isEmpty())
            throw new NotFoundException("Course not found...");

        Course dbCourse = courseOptional.get();
        if (courseUpdates.getCode() != null)
            dbCourse.setCode(courseUpdates.getCode());
        if (courseUpdates.getName() != null)
            dbCourse.setName(courseUpdates.getName());
        if (courseUpdates.getDescription() != null)
            dbCourse.setDescription(courseUpdates.getDescription());
        if (courseUpdates.getCreditHours() != null)
            dbCourse.setCreditHours(courseUpdates.getCreditHours());

        courseRepository.save(dbCourse);
        return dbCourse.toResponse();
    }

    public boolean deleteCourse(Integer id) throws NotFoundException {
        Optional<Course> courseOptional = courseRepository.findById(id);
        if(courseOptional.isEmpty())
            throw new NotFoundException("Course not found...");
        courseRepository.delete(courseOptional.get());
        return true;
    }

    public String deleteAllCourses() {
        courseRepository.deleteAll();
        return "Course table is now empty...";
    }
}
