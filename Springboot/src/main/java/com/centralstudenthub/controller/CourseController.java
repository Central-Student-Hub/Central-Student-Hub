package com.centralstudenthub.controller;

import com.centralstudenthub.Model.Request.CourseRequest;
import com.centralstudenthub.Model.Response.CourseResponse;
import com.centralstudenthub.exception.AllCoursesAlreadyExistsException;
import com.centralstudenthub.exception.ConflictException;
import com.centralstudenthub.exception.CourseAlreadyExistsException;
import com.centralstudenthub.exception.CourseNotFoundException;
import com.centralstudenthub.service.CoursePrerequisiteService;
import com.centralstudenthub.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/Course")
public class CourseController {
    private final Logger logger = Logger.getLogger(CourseController.class.getName());
    private final CourseService courseService;
    private final CoursePrerequisiteService coursePrerequisiteService;

    @Autowired
    public CourseController(CourseService courseService, CoursePrerequisiteService coursePrerequisiteService) {
        this.courseService = courseService;
        this.coursePrerequisiteService = coursePrerequisiteService;
    }

    @PostMapping("/addCourse")
    public CourseResponse addCourse(@Valid @RequestBody CourseRequest course) throws CourseAlreadyExistsException {
        logger.info("Class: CourseController, Method: addCourse");
        return courseService.addCourse(course);
    }

    @PostMapping("/addCourses")
    public List<CourseResponse> addCourses(@Valid @RequestBody CourseRequest[] courses) throws AllCoursesAlreadyExistsException {
        logger.info("Class: CourseController, Method: addCourses");
        return courseService.addCourses(courses);
    }

    @GetMapping("/getCourse/{id}")
    public CourseResponse getCourse(@PathVariable("id") int id) throws CourseNotFoundException {
        logger.info("Class: CourseController, Method: getCourse");
        return courseService.getCourse(id);
    }

    @GetMapping("/getAllCourses")
    public List<CourseResponse> getAllCourses() throws CourseNotFoundException {
        logger.info("Class: CourseController, Method: getAllCourses");
        return courseService.getAllCourses();
    }

    @PutMapping("/updateCourse/{id}")
    public CourseResponse updateCourse(@PathVariable("id") int id, @RequestBody CourseRequest courseUpdates) throws
            CourseNotFoundException {
        logger.info("Class: CourseController, Method: updateCourse");
        return courseService.updateCourse(id, courseUpdates);
    }

    @DeleteMapping("/deleteCourse/{id}")
    public boolean deleteCourse(@PathVariable("id") int id) throws CourseNotFoundException {
        logger.info("Class: CourseController, Method: deleteCourse");
        return courseService.deleteCourse(id);
    }

    @DeleteMapping("/deleteAllCourses")
    public String deleteAllCourses() {
        logger.info("Class: CourseController, Method: deleteAllCourses");
        return courseService.deleteAllCourses();
    }

    @PostMapping("/addCoursePrerequisite")
    public boolean addCoursePrerequisite(@RequestParam int courseId, @RequestParam int prerequisiteId) throws
            CourseNotFoundException, ConflictException, CourseAlreadyExistsException {
        logger.info("Class: CourseController, Method: addCoursePrerequisite");
        return coursePrerequisiteService.addCoursePrerequisite(courseId, prerequisiteId);
    }

}
