package com.centralstudenthub.entity.student_profile.course.course_prerequisites;

import com.centralstudenthub.entity.student_profile.course.Course;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CoursePrerequisiteId implements Serializable {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "prerequisiteId", nullable = false)
    private Course prerequisite;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "courseId", nullable = false)
    private Course course;
}
