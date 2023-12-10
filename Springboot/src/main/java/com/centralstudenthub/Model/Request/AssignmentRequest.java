package com.centralstudenthub.Model.Request;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssignmentRequest {
    private String assignmentName;
    private String description;
    private LocalDate dueDate;
    private Long semCourseId;
}
