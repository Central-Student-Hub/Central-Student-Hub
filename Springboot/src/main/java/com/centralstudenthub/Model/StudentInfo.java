package com.centralstudenthub.Model;

import com.centralstudenthub.entity.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentInfo {
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
    private List<StudentContact> studentStudentContacts;
    private List<Warning> studentWarnings;
    private List<StudentSemesterCourseGrade> studentSemesterCourseGrades;
    private List<AssignmentAnswer> studentAssignmentAnswers;
    private List<Registration> studentRegistrations;
}
