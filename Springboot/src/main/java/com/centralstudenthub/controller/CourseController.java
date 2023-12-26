package com.centralstudenthub.controller;

import com.centralstudenthub.Model.Request.CourseRequest;
import com.centralstudenthub.Model.Response.CourseResponse;
import com.centralstudenthub.Model.Response.StudentProfileResponse;
import com.centralstudenthub.exception.*;
import com.centralstudenthub.service.CoursePrerequisiteService;
import com.centralstudenthub.service.CourseService;
import com.centralstudenthub.service.StudentCourseGradeService;
import jakarta.validation.Valid;
import org.antlr.v4.runtime.misc.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@CrossOrigin(value = "http://localhost:3000", allowCredentials = "true", allowedHeaders = "*")
@RequestMapping("/Course")
public class CourseController {
    private final Logger logger = Logger.getLogger(CourseController.class.getName());
    private final CourseService courseService;
    private final CoursePrerequisiteService coursePrerequisiteService;
    private final StudentCourseGradeService studentCourseGradeService;

    @Autowired
    public CourseController(CourseService courseService, CoursePrerequisiteService coursePrerequisiteService,
                            StudentCourseGradeService studentCourseGradeService) {
        this.courseService = courseService;
        this.coursePrerequisiteService = coursePrerequisiteService;
        this.studentCourseGradeService = studentCourseGradeService;
    }

    @PostMapping("/addCourse")
    public Integer addCourse(@Valid @RequestBody CourseRequest course) throws AlreadyExistsException {
        logger.info("Class: CourseController, Method: addCourse");
        return courseService.addCourse(course);
    }

    @PostMapping("/addCourses")
    public List<CourseResponse> addCourses(@Valid @RequestBody CourseRequest[] courses) throws AlreadyExistsException {
        logger.info("Class: CourseController, Method: addCourses");
        return courseService.addCourses(courses);
    }

    @GetMapping("/getCourse/{id}")
    public CourseResponse getCourse(@PathVariable("id") Integer id) throws NotFoundException {
        logger.info("Class: CourseController, Method: getCourse");
        return courseService.getCourse(id);
    }

    @GetMapping("/getAllCourses")
    public List<CourseResponse> getAllCourses() {
        logger.info("Class: CourseController, Method: getAllCourses");
        return courseService.getAllCourses();
    }

    @PutMapping("/updateCourse/{id}")
    public CourseResponse updateCourse(@PathVariable("id") Integer id, @RequestBody CourseRequest courseUpdates) throws
            NotFoundException {
        logger.info("Class: CourseController, Method: updateCourse");
        return courseService.updateCourse(id, courseUpdates);
    }

    @DeleteMapping("/deleteCourse/{id}")
    public boolean deleteCourse(@PathVariable("id") Integer id) throws NotFoundException {
        logger.info("Class: CourseController, Method: deleteCourse");
        return courseService.deleteCourse(id);
    }

    @DeleteMapping("/deleteAllCourses")
    public boolean deleteAllCourses() {
        logger.info("Class: CourseController, Method: deleteAllCourses");
        return courseService.deleteAllCourses();
    }

    @PostMapping("/addCoursePrerequisite")
    public boolean addCoursePrerequisite(@RequestParam Integer courseId, @RequestParam Integer prerequisiteId) throws
            NotFoundException, DatabaseLogicalConstraintException, AlreadyExistsException {
        logger.info("Class: CourseController, Method: addCoursePrerequisite");
        return coursePrerequisiteService.addCoursePrerequisite(courseId, prerequisiteId);
    }

    @GetMapping("/getCoursePrerequisites/{courseId}")
    public List<CourseResponse> getCoursePrerequisites(@PathVariable("courseId") Integer courseId) throws NotFoundException {
        logger.info("Class: CourseController, Method: getCoursePrerequisites");
        return coursePrerequisiteService.getCoursePrerequisites(courseId);
    }

    @DeleteMapping("/deleteCoursePrerequisite/{courseId}/{prerequisiteId}")
    public boolean deleteCoursePrerequisite(@PathVariable("courseId") Integer courseId,
                                            @PathVariable("prerequisiteId") Integer prerequisiteId) throws NotFoundException {
        logger.info("Class: CourseController, Method: deleteCoursePrerequisite");
        return coursePrerequisiteService.deleteCoursePrerequisite(courseId, prerequisiteId);
    }

//    @PostMapping("/addStudentCourseGrade")
//    public boolean addStudentCourseGrade(@RequestBody StudentGradeRequest request) throws NotFoundException,
//            AlreadyExistsException {
//        logger.info("Class: CourseController, Method: addStudentCourseGrade");
//        return studentCourseGradeService.addStudentCourseGrade(request.getCourseId(), request.getStudentId(),
//                request.getStudentGrade());
//    }

    @GetMapping("/getStudentCourseGrade/{studentId}/{courseId}")
    public Double getStudentCourseGrade(@PathVariable("studentId") Integer studentId, @PathVariable("courseId") Integer courseId)
            throws NotFoundException {
        logger.info("Class: CourseController, Method: getStudentCourseGrade");
        return studentCourseGradeService.getStudentCourseGrade(studentId, courseId);
    }

//    @GetMapping("/getStudentGrades/{studentId}")
//    public List<Pair<CourseResponse, Double>> getStudentGrades(@PathVariable("studentId") Integer studentId) throws
//            NotFoundException {
//        logger.info("Class: CourseController, Method: getStudentGrades");
//        return studentCourseGradeService.getStudentGrades(studentId);
//    }
//
//    @GetMapping("/getCourseGrades/{courseId}")
//    public List<Pair<StudentProfileResponse, Double>> getCourseGrades(@PathVariable("courseId") Integer courseId) throws
//            NotFoundException {
//        logger.info("Class: CourseController, Method: getCourseGrades");
//        return studentCourseGradeService.getCourseGrades(courseId);
//    }

    @PutMapping("updateCourseGrade/{studentId}/{courseId}")
    public boolean updateCourseGrade(@PathVariable("studentId") Integer studentId, @PathVariable("courseId") Integer courseId,
                                     @RequestParam double newGrade) throws NotFoundException {
        logger.info("Class: CourseController, Method: updateCourseGrade");
        return studentCourseGradeService.updateCourseGrade(studentId, courseId, newGrade);
    }

    @DeleteMapping("deleteCourseGrade/{studentId}/{courseId}")
    public boolean deleteCourseGrade(@PathVariable("studentId") Integer studentId, @PathVariable("courseId") Integer courseId)
            throws NotFoundException {
        logger.info("Class: CourseController, Method: deleteCourseGrade");
        return studentCourseGradeService.deleteCourseGrade(studentId, courseId);
    }

}
