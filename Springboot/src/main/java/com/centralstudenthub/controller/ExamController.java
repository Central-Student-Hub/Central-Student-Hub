package com.centralstudenthub.controller;

import com.centralstudenthub.Model.Request.BatchGradeRequest;
import com.centralstudenthub.Model.Request.ExamRequest;
import com.centralstudenthub.Model.Response.ExamResponse;
import com.centralstudenthub.exception.AlreadyExistsException;
import com.centralstudenthub.exception.NotFoundException;
import com.centralstudenthub.service.ExamService;
import com.centralstudenthub.service.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(value = "http://localhost:3000", allowCredentials = "true", allowedHeaders = "*")
public class ExamController {
    @Autowired
    private ExamService examService;
    @Autowired
    private JwtService jwtService;

    @GetMapping("/Exams")
    public List<ExamResponse> getExamList(HttpServletRequest request) throws NotFoundException {
        int id = jwtService.extractId(jwtService.token(request));
        return examService.getExamsList(id);
    }

    @PostMapping("/Exams/distribute")
    public boolean distributeStudents(@RequestBody ExamRequest request) {
        return examService.distributeStudents(request);
    }

    @PostMapping("/Exams/batchGrade")
    public boolean batchGrade(@RequestBody BatchGradeRequest request) throws AlreadyExistsException, NotFoundException {
        return examService.batchGrade(request);
    }
}
