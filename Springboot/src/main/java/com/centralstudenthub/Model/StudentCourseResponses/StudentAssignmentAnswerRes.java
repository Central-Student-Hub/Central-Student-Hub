package com.centralstudenthub.Model.StudentCourseResponses;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentAssignmentAnswerRes {

    private Long assignmentId;
    private String answerPath;
    private Double grade;
}
