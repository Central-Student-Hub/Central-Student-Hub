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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer studentId;

    private String firstName;
    private String lastName;
    private String biography;
    private String profilePictureUrl;
    private String major;
    private String minor;
    private Integer level;
    private Integer noOfHours;
    private Double gpa;

    @OneToMany(mappedBy = "student")
    private Set<StudentContact> contacts;

    @OneToMany(mappedBy = "student")
    private Set<Warning> warnings;

    @OneToMany(mappedBy = "student")
    private Set<StudentCourseGrade> grades;

    @OneToMany(mappedBy = "student")
    private Set<StudentAssignmentAnswer> assignmentAnswers;

    @OneToMany(mappedBy = "student")
    private Set<Registration> registrations;

}
