package com.centralstudenthub.entity;

import com.centralstudenthub.Model.Semester;
import jakarta.persistence.*;

import java.util.Set;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(name = "semester_course")
public class SemesterCourse {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long semCourseId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "courseId", nullable = false)
    private Course course;

    @Enumerated(EnumType.STRING)
    private Semester semester;

    private Integer maxSeats;

    @OneToMany(mappedBy = "semCourse")
    private Set<StudentCourseGrade> grades;

    @OneToMany(mappedBy = "semCourse")
    private Set<CourseMaterialPath> materialPaths;

    @OneToMany(mappedBy = "semCourse")
    private Set<CourseMember> members;

    @OneToMany(mappedBy = "semCourse")
    private Set<Assignment> assignments;

    @OneToMany(mappedBy = "semCourse")
    private Set<Announcement> announcements;

    @OneToMany(mappedBy = "semCourse")
    private Set<Feedback> feedbacks;

    @OneToMany(mappedBy = "semCourse")
    private Set<Session> sessions;

    @OneToMany(mappedBy = "semCourse")
    private Set<Registration> registrations;

}
