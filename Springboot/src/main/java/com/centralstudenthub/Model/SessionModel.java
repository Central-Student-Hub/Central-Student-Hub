package com.centralstudenthub.Model;

import com.centralstudenthub.entity.student_profile.course.semester_courses.sessions.Session;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class SessionModel {
    Long id;
    Integer period;
    String weekday;
    SessionType sessionType;
    String teacherName;
    LocationModel location;

    public Session toSession() {
        return Session.builder()
                .sessionId(id)
                .period(period)
                .weekDay(weekday)
                .sessionType(sessionType)
                .location(location.toLocation())
                .build();
    }
}
