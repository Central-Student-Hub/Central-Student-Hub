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
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long assignmentId;

    @Column
    private String assignmentName;

    @Column(name = "\"description\"")
    private String description;

    @Column
    private LocalDate dueDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "semCourseId", nullable = false)
    private SemesterCourse semCourse;

    @OneToMany(mappedBy = "assignment")
    private Set<AssignmentMaterialPath> assignmentAssignmentMaterialPaths;

    @OneToMany(mappedBy = "assignment")
    private Set<AssignmentAnswer> assignmentAssignmentAnswers;

}
