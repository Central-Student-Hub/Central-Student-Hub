package com.centralstudenthub.controller;

import com.centralstudenthub.service.SemesterCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/SemesterCourse")
public class SemesterCourseController {
    private final Logger logger = Logger.getLogger(CourseController.class.getName());

    private final SemesterCourseService SemesterCourseService;

    @Autowired
    public SemesterCourseController(SemesterCourseService SemesterCourseService) {
        this.SemesterCourseService = SemesterCourseService;
    }

    //TODO: Add methods here
}
