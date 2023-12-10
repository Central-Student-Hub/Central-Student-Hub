package com.centralstudenthub.entity;

import com.centralstudenthub.Model.SessionType;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "session")
public class Session {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long sessionId;

    @Column
    private String teacher;

    @Column
    private Integer period;

    @Column
    private String weekDay;

    @Column
    @Enumerated(EnumType.STRING)
    private SessionType sessionType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "semCourseId", nullable = false)
    private SemesterCourse semCourse;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room", nullable = false)
    @JoinColumn(name = "building", nullable = false)
    private Location location;

}
