package com.centralstudenthub.controller;

import com.centralstudenthub.Model.Request.AssignmentMaterialPathRequest;
import com.centralstudenthub.Model.Request.AssignmentRequest;
import com.centralstudenthub.Model.Request.StudentAssignmentAnswerRequest;
import com.centralstudenthub.Model.StudentCourseResponses.StudentAssignmentRes;
import com.centralstudenthub.entity.student_profile.course.semester_courses.assignments.Assignment;
import com.centralstudenthub.service.AssignmentService;
import com.centralstudenthub.service.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(value = "http://localhost:3000", allowCredentials = "true", allowedHeaders = "*")
@RequestMapping("/Assignment")
public class AssignmentController {
    private final AssignmentService assignmentService;
    private final JwtService jwtService;

    @Autowired
    public AssignmentController(AssignmentService assignmentService, JwtService jwtService) {
        this.assignmentService = assignmentService;
        this.jwtService = jwtService;
    }

    @PostMapping("/addAssignment")
    public ResponseEntity<Boolean> addAssignment(@RequestBody AssignmentRequest assignmentRequest){
        boolean response  = assignmentService.addAssignment(assignmentRequest);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/getAssignment")
    public ResponseEntity<Assignment> getAssignment(@RequestParam Long assignmentId ){
        Assignment assignment  = assignmentService.getAssignment(assignmentId);
        return ResponseEntity.ok().body(assignment);
    }

    @GetMapping("/getAllAssignmentByCourseId")
    public ResponseEntity<List<StudentAssignmentRes>> getAllAssignmentByCourseId(@RequestParam Long courseId, HttpServletRequest httpServletRequest){
        int studentId = jwtService.extractId(jwtService.token(httpServletRequest));
        return ResponseEntity.ok(assignmentService.getAllAssignmentByCourseId(courseId,studentId));
    }

    @PostMapping("/addAnswer")
    public ResponseEntity<Boolean> addAssignmentsAnswer(@RequestBody StudentAssignmentAnswerRequest ansRequest,HttpServletRequest httpServletRequest ){
        int studentId = jwtService.extractId(jwtService.token(httpServletRequest));
        boolean response = assignmentService.addAssignmentAnswer(ansRequest,studentId);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/addMaterialPath")
    public ResponseEntity<Boolean> addAssignmentMaterialPath
            (@RequestBody AssignmentMaterialPathRequest MaterialPassRequest ){

        boolean response = assignmentService.addAssignmentMaterialPath(MaterialPassRequest);
        return ResponseEntity.ok().body(response);
    }
}
