package com.centralstudenthub.entity.student_profile.course.student_course_grades;

import com.centralstudenthub.Model.Response.student_profile.course.StudentCourseGradeResponse;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "student_course_grade")
public class StudentCourseGrade {

    @EmbeddedId
    private StudentCourseGradeId id;

    private Double studentGrade;

    public StudentCourseGradeResponse toResponse() {
        return StudentCourseGradeResponse.builder()
                .courseName(id.getCourse().getName())
                .grade(studentGrade)
                .build();
    }
}
