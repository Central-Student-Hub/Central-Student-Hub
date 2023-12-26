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
    private String weekDay;
    private SessionType sessionType;
    private String teacherName;
    private LocationResponse location;

    public Session toEntity() {
        return Session.builder()
                .sessionId(id)
                .period(period)
                .weekDay(weekDay)
                .sessionType(sessionType)
                .location(location.toEntity())
                .build();
    }
}
