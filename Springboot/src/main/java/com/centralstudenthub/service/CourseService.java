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

    public CourseResponse addCourse(CourseRequest course) throws AlreadyExistsException {
        if(courseRepository.existsByCode(course.getCode()))
            throw new AlreadyExistsException("Course already exists...");

        Course savedCourse = Course.builder()
                .code(course.getCode())
                .name(course.getName())
                .description(course.getDescription())
                .creditHours(course.getCreditHours())
                .build();
        courseRepository.save(savedCourse);
        return savedCourse.toCourseResponse();
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
            savedCourses.add(savedCourse.toCourseResponse());
            addedCoursesCount++;
        }
        if (addedCoursesCount == 0)
            throw new AlreadyExistsException("All courses already exists...");
        return savedCourses;
    }

    public CourseResponse getCourse(int id) throws NotFoundException {
        Optional<Course> courseOptional = courseRepository.findById(id);
        if(courseOptional.isEmpty())
            throw new NotFoundException("Course not found...");

        return courseOptional.get().toCourseResponse();
    }

    public List<CourseResponse> getAllCourses() {
        List<Course> courses = courseRepository.findAll();
        List<CourseResponse> courseResponses = new ArrayList<>();
        for(Course course: courses)
            courseResponses.add(course.toCourseResponse());
        return courseResponses;
    }

    public CourseResponse updateCourse(int id, CourseRequest courseUpdates) throws NotFoundException {
        Optional<Course> courseOptional = courseRepository.findById(id);
        if(courseOptional.isEmpty())
            throw new NotFoundException("Course not found...");

        Course dbCourse = courseOptional.get();
        dbCourse.setCode(courseUpdates.getCode());
        dbCourse.setName(courseUpdates.getName());
        dbCourse.setDescription(courseUpdates.getDescription());
        dbCourse.setCreditHours(courseUpdates.getCreditHours());
        courseRepository.save(dbCourse);
        return dbCourse.toCourseResponse();
    }

    public boolean deleteCourse(int id) throws NotFoundException {
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
