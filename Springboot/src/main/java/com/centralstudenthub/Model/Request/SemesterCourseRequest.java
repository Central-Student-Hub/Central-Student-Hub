package com.centralstudenthub.Model.Request;

import com.centralstudenthub.Model.Semester;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SemesterCourseRequest {
    private Integer courseId;
    private Semester semester;
    private Integer maxSeats;
}
