package com.centralstudenthub.Model.Response.sessions;

import com.centralstudenthub.Model.SessionType;
import com.centralstudenthub.entity.sessions.Session;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SessionResponse {
    private Long id;
    private Integer period;
    private String weekday;
    private SessionType sessionType;
    private String teacherName;
    private LocationResponse location;

    public Session toEntity() {
        return Session.builder()
                .sessionId(id)
                .period(period)
                .weekDay(weekday)
                .sessionType(sessionType)
                .location(location.toEntity())
                .build();
    }
}
