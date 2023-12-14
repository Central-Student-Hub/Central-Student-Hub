package com.centralstudenthub.Model.Response;

import com.centralstudenthub.Model.Semester;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SemesterCourseResponse {
    private Long semCourseId;
    private String code;
    private String name;
    private String description;
    private Integer creditHours;
    private Semester semester;
    private Integer maxSeats;
}
