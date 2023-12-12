package com.centralstudenthub.entity.student_profile.course.semester_courses.course_material_paths;

import com.centralstudenthub.entity.student_profile.course.semester_courses.SemesterCourse;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseMaterialPathId implements Serializable {

    @Column(nullable = false, updatable = false)
    private String materialPath;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "semCourseId", nullable = false)
    private SemesterCourse semCourse;
}
