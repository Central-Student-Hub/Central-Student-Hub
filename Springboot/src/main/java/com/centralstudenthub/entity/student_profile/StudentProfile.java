package com.centralstudenthub.entity.student_profile;

import com.centralstudenthub.Model.Request.StudentProfileRequest;
import com.centralstudenthub.entity.student_profile.course.student_course_grades.StudentCourseGrade;
import com.centralstudenthub.entity.student_profile.course.semester_courses.registrations.Registration;
import com.centralstudenthub.entity.student_profile.course.semester_courses.assignments.student_assignment_answers.StudentAssignmentAnswer;
import com.centralstudenthub.entity.student_profile.student_contacts.StudentContact;
import jakarta.persistence.*;

import java.util.List;

import lombok.*;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "student_profile")
public class StudentProfile {

    @Id
    @Column(nullable = false, updatable = false)
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

    @OneToMany(mappedBy = "id.student")
    private List<StudentCourseGrade> grades;

    @OneToMany(mappedBy = "studentAssignmentAnswerId.student")
    private List<StudentAssignmentAnswer> assignmentAnswers;

    @OneToMany(mappedBy = "id.student")
    private List<Registration> registrations;

    public StudentProfileRequest modelFromStudentProfile() {
        return StudentProfileRequest.builder()
                .firstName(firstName)
                .lastName(lastName)
                .biography(biography)
                .profilePictureUrl(profilePictureUrl)
                .major(major)
                .minor(minor)
                .level(level)
                .noOfHours(noOfHours)
                .gpa(gpa)
                .build();
    }
}
