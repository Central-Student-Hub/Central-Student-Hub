package com.centralstudenthub.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(name = "course_prerequisite")
public class CoursePrerequisite {

    @Id
    @Column(nullable = false, updatable = false)
    private String prerequisite;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "courseId", nullable = false)
    private Course course;

}
