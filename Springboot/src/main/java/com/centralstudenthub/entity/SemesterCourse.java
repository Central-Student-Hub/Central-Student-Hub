package com.centralstudenthub.entity;

import com.centralstudenthub.Model.Semester;
import jakarta.persistence.*;

import java.util.List;

import lombok.*;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
    private List<StudentCourseGrade> grades;

    @OneToMany(mappedBy = "semCourse")
    private List<CourseMaterialPath> materialPaths;

    @OneToMany(mappedBy = "semCourse")
    private List<CourseMember> members;

    @OneToMany(mappedBy = "semCourse")
    private List<Assignment> assignments;

    @OneToMany(mappedBy = "semCourse")
    private List<Announcement> announcements;

    @OneToMany(mappedBy = "semCourse")
    private List<Feedback> feedbacks;

    @OneToMany(mappedBy = "semCourse")
    private List<Session> sessions;

    @OneToMany(mappedBy = "semCourse")
    private List<Registration> registrations;

}
