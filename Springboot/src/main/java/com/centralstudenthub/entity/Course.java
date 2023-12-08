package com.centralstudenthub.entity;

import jakarta.persistence.*;

import java.util.Set;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(name = "course")
public class Course {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer courseId;

    @Column(nullable = false, unique = true)
    private String code;

    @Column
    private Integer creditHours;

    @Column
    private String name;

    @Column(name = "\"description\"")
    private String description;

    @Column
    private String semester;

    @OneToMany(mappedBy = "course")
    private Set<CoursePrerequisite> courseCoursePrerequisites;

    @OneToMany(mappedBy = "course")
    private Set<SemesterCourse> courseSemesterCourses;

}
