package com.centralstudenthub.entity.exam;

import com.centralstudenthub.entity.student_profile.StudentProfile;
import com.centralstudenthub.entity.student_profile.course.semester_courses.SemesterCourse;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ExamId implements Serializable {

    @Column(name = "semCourseId")
    private Long semCourseId;

    @Column(name = "studentId")
    private Integer studentId;
}


