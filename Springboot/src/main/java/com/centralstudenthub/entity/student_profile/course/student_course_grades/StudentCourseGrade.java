package com.centralstudenthub.entity.student_profile.course.student_course_grades;

import com.centralstudenthub.Model.Request.StudentCourseGradeModel;
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

    public StudentCourseGradeModel modelFromGrade() {
        return StudentCourseGradeModel.builder()
                .courseName(id.getCourse().getName())
                .grade(studentGrade)
                .build();
    }
}
