package com.centralstudenthub.Model.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CoursePrerequisiteRequest {
    private Integer courseId;
    private Integer prerequisiteId;
}
