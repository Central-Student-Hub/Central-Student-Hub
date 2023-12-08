package com.centralstudenthub.entity;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long semCourseId;

    @Column
    private Integer maxSeats;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "courseId", nullable = false)
    private Course course;

    @OneToMany(mappedBy = "semCourse")
    private Set<StudentSemesterCourseGrade> semCourseStudentSemesterCourseGrades;

    @OneToMany(mappedBy = "semCourse")
    private Set<SemesterCourseMaterialPath> semCourseSemesterCourseMaterialPaths;

    @OneToMany(mappedBy = "semCourse")
    private Set<SemesterCourseMember> semCourseSemesterCourseMembers;

    @OneToMany(mappedBy = "semCourse")
    private Set<Assignment> semCourseAssignments;

    @OneToMany(mappedBy = "semCourse")
    private Set<Announcement> semCourseAnnouncements;

    @OneToMany(mappedBy = "semCourse")
    private Set<Feedback> semCourseFeedbacks;

    @OneToMany(mappedBy = "semCourse")
    private Set<Session> semCourseSessions;

    @OneToMany(mappedBy = "semCourse")
    private Set<Registration> semCourseRegistrations;

}
