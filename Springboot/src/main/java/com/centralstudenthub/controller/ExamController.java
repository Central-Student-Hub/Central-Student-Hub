package com.centralstudenthub.controller;

import com.centralstudenthub.Model.Request.ExamRequest;
import com.centralstudenthub.Model.Response.ExamResponse;
import com.centralstudenthub.exception.NotFoundException;
import com.centralstudenthub.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(value = "http://localhost:3000", allowCredentials = "true", allowedHeaders = "*")
public class ExamController {
    @Autowired
    private ExamService examService;

    @GetMapping("/Exams/{id}")
    public List<ExamResponse> getExamList(@RequestParam Integer id) throws NotFoundException {
        return examService.getExamsList(id);
    }

    @PostMapping("/Exams/distribute")
    public boolean distributeStudents(@RequestBody ExamRequest request) {
        return examService.distributeStudents(request);
    }
}
