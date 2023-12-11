package com.centralstudenthub.entity;

import jakarta.persistence.*;

import java.util.List;

import lombok.*;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
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

    @OneToMany(mappedBy = "id.student")
    private List<StudentContact> contacts;

    @OneToMany(mappedBy = "student")
    private List<Warning> warnings;

    @OneToMany(mappedBy = "student")
    private List<StudentCourseGrade> grades;

    @OneToMany(mappedBy = "studentAssignmentAnswerId.student")
    private List<StudentAssignmentAnswer> assignmentAnswers;

    @OneToMany(mappedBy = "student")
    private List<Registration> registrations;

}
