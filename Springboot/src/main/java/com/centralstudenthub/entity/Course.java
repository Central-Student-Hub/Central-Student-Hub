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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer courseId;

    @Column(nullable = false, unique = true)
    private String code;

    private String name;
    private String description;
    private Integer creditHours;

    @OneToMany(mappedBy = "course")
    private Set<CoursePrerequisite> prerequisites;

    @OneToMany(mappedBy = "course")
    private Set<SemesterCourse> semesterCourses;

}
