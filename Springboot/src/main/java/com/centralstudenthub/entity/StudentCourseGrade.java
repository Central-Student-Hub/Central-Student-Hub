package com.centralstudenthub.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "student_course_grade")
public class StudentCourseGrade {

    @EmbeddedId
    private StudentCourseGradeId id;

    private Double studentGrade;
}
