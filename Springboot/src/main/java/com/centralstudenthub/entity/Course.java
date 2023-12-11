package com.centralstudenthub.entity;

import jakarta.persistence.*;

import java.util.List;

import lombok.*;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "course")
@Data
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
    private List<CoursePrerequisite> prerequisites;

    @OneToMany(mappedBy = "course")
    private List<SemesterCourse> semesterCourses;

}
