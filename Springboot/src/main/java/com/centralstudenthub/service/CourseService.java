package com.centralstudenthub.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.centralstudenthub.Model.Request.CourseRequest;
import com.centralstudenthub.Model.Response.student_profile.course.CourseResponse;
import com.centralstudenthub.entity.student_profile.course.Course;
import com.centralstudenthub.exception.NotFoundException;
import com.centralstudenthub.repository.CourseRepository;

@Service
public class CourseService {
    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Integer addCourse(CourseRequest course) {
        if(courseRepository.existsByCode(course.getCode())) return -1;
        return courseRepository.save(course.toEntity()).getCourseId();
    }

    public boolean addCourses(CourseRequest[] coursesRequests) {
        int addedCoursesCount = 0;
        List<Course> courses = new ArrayList<>();
        for(CourseRequest course: coursesRequests) {
            if(courseRepository.existsByCode(course.getCode())) continue;
            courses.add(course.toEntity());
            addedCoursesCount++;
        }
        courseRepository.saveAll(courses);
        return addedCoursesCount != 0;
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
        if (courseUpdates.getCode() != null) dbCourse.setCode(courseUpdates.getCode());
        if (courseUpdates.getName() != null) dbCourse.setName(courseUpdates.getName());
        if (courseUpdates.getDescription() != null) dbCourse.setDescription(courseUpdates.getDescription());
        if (courseUpdates.getCreditHours() != null) dbCourse.setCreditHours(courseUpdates.getCreditHours());
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
