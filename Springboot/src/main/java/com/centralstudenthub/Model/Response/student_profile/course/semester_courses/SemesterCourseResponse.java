package com.centralstudenthub.Model.Response.student_profile.course.semester_courses;

import com.centralstudenthub.Model.Response.sessions.SessionResponse;
import com.centralstudenthub.Model.Semester;
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
    private List<SessionResponse> sessions;
}