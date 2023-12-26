package com.centralstudenthub.Model.Response;

import com.centralstudenthub.Model.Semester;
import com.centralstudenthub.Model.SessionModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
    private List<String> prerequisitesCodes;
    private Semester semester;
    private Integer maxSeats;
    private Integer remainingSeats;
    private List<SessionModel> sessions;
}
