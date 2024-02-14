package com.centralstudenthub.Model.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentAssignmentAnswerRequest {
    private String answerPath;
    private Long assignmentId;
}
