package com.centralstudenthub.entity.student_profile.course.student_course_grades;

import com.centralstudenthub.entity.student_profile.StudentProfile;
import com.centralstudenthub.entity.student_profile.course.Course;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentCourseGradeId implements Serializable {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "courseId", nullable = false)
    private Course course;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "studentId", nullable = false)
    private StudentProfile student;
}
