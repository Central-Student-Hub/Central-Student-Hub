package com.centralstudenthub.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "course_prerequisite")
public class CoursePrerequisite {

    @EmbeddedId
    private CoursePrerequisiteId id;
}
