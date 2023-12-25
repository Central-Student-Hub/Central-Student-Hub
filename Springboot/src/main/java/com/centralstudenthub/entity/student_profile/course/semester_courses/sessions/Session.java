package com.centralstudenthub.entity.student_profile.course.semester_courses.sessions;

import com.centralstudenthub.Model.Response.SessionResponse;
import com.centralstudenthub.Model.SessionModel;
import com.centralstudenthub.Model.SessionType;
import com.centralstudenthub.entity.student_profile.course.semester_courses.SemesterCourse;
import com.centralstudenthub.entity.student_profile.course.semester_courses.sessions.location.Location;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "semCourseId", nullable = false)
    private SemesterCourse semCourse;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacherId", nullable = false)
    private TeachingStaffProfile teacher;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room", nullable = false)
    @JoinColumn(name = "building", nullable = false)
    private Location location;

    public SessionModel toModel() {
        return SessionModel.builder()
                .id(sessionId)
                .period(period)
                .weekday(weekDay)
                .sessionType(sessionType)
                .location(location.toModel())
                .teacherName(teacher.getFirstName() + teacher.getLastName())
                .build();
    }

}
