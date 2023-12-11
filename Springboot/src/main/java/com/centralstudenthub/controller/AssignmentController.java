package com.centralstudenthub.controller;

import com.centralstudenthub.Model.Request.AssignmentMaterialPathRequest;
import com.centralstudenthub.Model.Request.AssignmentRequest;
import com.centralstudenthub.Model.Request.StudentAssignmentAnswerRequest;
import com.centralstudenthub.entity.Assignment;
import com.centralstudenthub.service.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/Assignment")
public class AssignmentController {

    @Autowired
    private AssignmentService assignmentService;

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

    @PostMapping("/addAnswer")
    public ResponseEntity<Boolean> addAssignmentAnswer(@RequestBody StudentAssignmentAnswerRequest ansRequest ){
        boolean response = assignmentService.addAssignmentAnswer(ansRequest);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/addMaterialPath")
    public ResponseEntity<Boolean> addAssignmentMaterialPath
            (@RequestBody AssignmentMaterialPathRequest MaterialPassRequest ){

        boolean response = assignmentService.addAssignmentMaterialPath(MaterialPassRequest);
        return ResponseEntity.ok().body(response);
    }
}
