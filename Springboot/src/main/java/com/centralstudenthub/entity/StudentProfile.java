package com.centralstudenthub.entity;

import jakarta.persistence.*;

import java.util.Set;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(name = "student_profile")
public class StudentProfile {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer studentId;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String biography;

    @Column
    private String profilePictureUrl;

    @Column
    private String major;

    @Column
    private String minor;

    @Column(nullable = false)
    private Integer level;

    @Column
    private Integer noOfHours;

    @Column
    private Double gpa;

    @OneToMany(mappedBy = "student")
    private Set<StudentContact> studentStudentContacts;

    @OneToMany(mappedBy = "student")
    private Set<Warning> studentWarnings;

    @OneToMany(mappedBy = "student")
    private Set<StudentSemesterCourseGrade> studentStudentSemesterCourseGrades;

    @OneToMany(mappedBy = "student")
    private Set<AssignmentAnswer> studentAssignmentAnswers;

    @OneToMany(mappedBy = "student")
    private Set<Registration> studentRegistrations;

}
