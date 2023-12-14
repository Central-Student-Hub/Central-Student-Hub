package com.centralstudenthub.Model.Request;

import com.centralstudenthub.entity.student_profile.Warning;
import com.centralstudenthub.entity.student_profile.course.semester_courses.assignments.student_assignment_answers.StudentAssignmentAnswer;
import com.centralstudenthub.entity.student_profile.course.semester_courses.registrations.Registration;
import com.centralstudenthub.entity.student_profile.course.student_course_grades.StudentCourseGrade;
import com.centralstudenthub.entity.student_profile.student_contacts.StudentContact;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentProfileRequest {
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
    private List<StudentContact> contacts;
    private List<Warning> warnings;
    private List<StudentCourseGrade> grades;
    private List<StudentAssignmentAnswer> assignmentAnswers;
    private List<Registration> registrations;
}
