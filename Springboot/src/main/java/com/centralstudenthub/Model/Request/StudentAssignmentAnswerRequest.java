package com.centralstudenthub.Model.Request;

import com.centralstudenthub.entity.Assignment;
import com.centralstudenthub.entity.StudentProfile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentAssignmentAnswerRequest {
    private String answerPath;
    private Double grade;
    private Long studentProfileId;
    private Long assignmentId;
}
