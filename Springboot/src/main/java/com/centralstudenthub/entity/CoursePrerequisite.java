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

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prerequisiteId", nullable = false)
    private Course prerequisite;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "courseId", nullable = false)
    private Course course;
}
