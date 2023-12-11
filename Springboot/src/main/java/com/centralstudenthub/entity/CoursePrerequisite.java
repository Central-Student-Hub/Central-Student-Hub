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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prerequisiteId", nullable = false)
    private Course prerequisite;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "courseId", nullable = false)
    private Course course;

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
