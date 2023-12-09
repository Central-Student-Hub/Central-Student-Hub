package com.centralstudenthub.Model.Request;

import com.centralstudenthub.entity.AssignmentAnswer;
import com.centralstudenthub.entity.AssignmentMaterialPath;
import com.centralstudenthub.entity.SemesterCourse;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

public class AssignmentRequest {
    private Long assignmentId;
    private String assignmentName;
    private String description;
    private LocalDate dueDate;
    private SemesterCourse semCourse;
    private List<AssignmentMaterialPath> assignmentAssignmentMaterialPaths;
    private List<AssignmentAnswer> assignmentAssignmentAnswers;
}
