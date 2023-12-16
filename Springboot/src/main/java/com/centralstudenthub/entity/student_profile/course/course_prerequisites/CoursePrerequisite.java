package com.centralstudenthub.entity.student_profile.course.course_prerequisites;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "course_prerequisite")
public class CoursePrerequisite {

    @EmbeddedId
    private CoursePrerequisiteId id;
}
