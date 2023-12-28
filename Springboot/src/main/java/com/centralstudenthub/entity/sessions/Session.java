package com.centralstudenthub.entity.sessions;

import com.centralstudenthub.Model.Response.sessions.SessionResponse;
import com.centralstudenthub.Model.SessionType;
import com.centralstudenthub.entity.sessions.location.Location;
import com.centralstudenthub.entity.student_profile.course.semester_courses.SemesterCourse;
import com.centralstudenthub.entity.teacher_profile.TeachingStaffProfile;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "session")
public class Session {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long sessionId;

    private Integer period;
    private String weekDay;

    @Enumerated(EnumType.STRING)
    private SessionType sessionType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "semCourseId", nullable = false)
    private SemesterCourse semCourse;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "teacherId", nullable = false)
    private TeachingStaffProfile teacher;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room", nullable = false)
    @JoinColumn(name = "building", nullable = false)
    private Location location;

    public SessionResponse toResponse() {
        return SessionResponse.builder()
                .id(sessionId)
                .period(period)
                .weekDay(weekDay)
                .sessionType(sessionType)
                .location(location.toResponse())
                .teacherName(teacher.getFirstName() + teacher.getLastName())
                .build();
    }

}
