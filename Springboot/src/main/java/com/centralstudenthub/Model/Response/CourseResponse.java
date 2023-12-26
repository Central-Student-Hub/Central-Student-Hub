package com.centralstudenthub.Model.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseResponse {
    private Integer courseId;
    private String code;
    private String name;
    private String description;
    private Integer creditHours;
}
