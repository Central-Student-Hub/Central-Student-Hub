package com.centralstudenthub.controller;

import com.centralstudenthub.entity.Assignment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:3000")
public class AssignmentController {

    @PostMapping("/addAssignment")
    public ResponseEntity<Boolean> addAssignment(@RequestBody Assignment assignmentRequest){

        System.out.println(assignmentRequest.getAssignmentName());
        return ResponseEntity.ok().body(true);
    }
}
