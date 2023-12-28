package com.centralstudenthub.Model.StudentCourseResponses;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentAssignmentRes {
    Long assignmentId;
    String assignmentName;
    String assignmentDescription;
    LocalDate assignmentDueDate;
    List<String> assignmentMaterialPaths;
}
