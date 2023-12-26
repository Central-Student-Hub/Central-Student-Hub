package com.centralstudenthub.entity.student_profile.course.course_prerequisites;

import com.centralstudenthub.Model.Response.student_profile.course.CoursePrerequisiteResponse;
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

    public CoursePrerequisiteResponse toResponse() {
        return CoursePrerequisiteResponse.builder()
                .courseId(id.getCourse().getCourseId())
                .prerequisiteId(id.getPrerequisite().getCourseId())
                .build();
    }
}
