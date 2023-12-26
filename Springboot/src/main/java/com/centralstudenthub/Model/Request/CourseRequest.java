package com.centralstudenthub.Model.Request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseRequest {
    @NotBlank(message = "Please, you should provide a course code...")
    private String code;
    private String name;
    private String description;
    private Integer creditHours;
}
