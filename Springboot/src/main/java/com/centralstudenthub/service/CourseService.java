package com.centralstudenthub.service;

import com.centralstudenthub.Model.Request.CourseRequest;
import com.centralstudenthub.Model.Response.CourseResponse;
import com.centralstudenthub.entity.student_profile.course.Course;
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

    public boolean addCourse(CourseRequest course) {
        if(courseRepository.existsByCode(course.getCode())) return false;
        Course savedCourse = Course.builder()
                .code(course.getCode())
                .name(course.getName())
                .description(course.getDescription())
                .creditHours(course.getCreditHours())
                .build();
        courseRepository.save(savedCourse);
        return true;
    }

    public boolean addCourses(CourseRequest[] courses) {
        int addedCoursesCount = 0;
        for(CourseRequest course: courses) {
            if(courseRepository.existsByCode(course.getCode())) continue;
            Course savedCourse = Course.builder()
                    .code(course.getCode())
                    .name(course.getName())
                    .description(course.getDescription())
                    .creditHours(course.getCreditHours())
                    .build();
            courseRepository.save(savedCourse);
            addedCoursesCount++;
        }
        return addedCoursesCount == 0;
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

    public boolean updateCourse(Integer id, CourseRequest courseUpdates) {
        Optional<Course> courseOptional = courseRepository.findById(id);
        if(courseOptional.isEmpty()) return false;
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
        return true;
    }

    public boolean deleteCourse(Integer id) {
        Optional<Course> courseOptional = courseRepository.findById(id);
        if(courseOptional.isEmpty()) return false;
        courseRepository.delete(courseOptional.get());
        return true;
    }

    public boolean deleteAllCourses() {
        courseRepository.deleteAll();
        return true;
    }
}
