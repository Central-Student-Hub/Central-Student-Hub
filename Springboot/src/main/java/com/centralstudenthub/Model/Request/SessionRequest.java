package com.centralstudenthub.Model.Request;

import com.centralstudenthub.Model.SessionType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SessionRequest {
    private Long semCourseId;
    private Integer teacherId;
    private Integer period;
    private String weekDay;
    private SessionType sessionType;
    private Integer building;
    private Integer room;
}
