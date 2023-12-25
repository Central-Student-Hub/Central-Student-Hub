package com.centralstudenthub.entity.exam;

import com.centralstudenthub.entity.student_profile.StudentProfile;
import com.centralstudenthub.entity.student_profile.course.semester_courses.SemesterCourse;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExamId implements Serializable {
    private StudentProfile studentProfile;
    private SemesterCourse course;
}
