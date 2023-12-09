package com.centralstudenthub.entity;

import jakarta.persistence.*;
import java.util.List;

import lombok.*;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
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
    private List<StudentContact> studentStudentContacts;

    @OneToMany(mappedBy = "student")
    private List<Warning> studentWarnings;

    @OneToMany(mappedBy = "student")
    private List<StudentSemesterCourseGrade> studentStudentSemesterCourseGrades;

    @OneToMany(mappedBy = "student")
    private List<AssignmentAnswer> studentAssignmentAnswers;

    @OneToMany(mappedBy = "student")
    private List<Registration> studentRegistrations;

}
