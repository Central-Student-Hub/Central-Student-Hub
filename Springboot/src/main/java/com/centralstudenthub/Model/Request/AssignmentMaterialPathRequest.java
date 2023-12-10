package com.centralstudenthub.Model.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssignmentMaterialPathRequest {
    String materialPath;
    Long assignmentId;
}
