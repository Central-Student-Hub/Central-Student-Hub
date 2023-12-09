package com.centralstudenthub.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(name = "assignment")
public class Assignment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long assignmentId;

    private String assignmentName;
    private String description;
    private LocalDate dueDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "semCourseId", nullable = false)
    private SemesterCourse semCourse;

    @OneToMany(mappedBy = "assignment")
    private Set<AssignmentMaterialPath> materialPaths;

    @OneToMany(mappedBy = "assignment")
    private Set<StudentAssignmentAnswer> answers;

}