package com.centralstudenthub.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

import lombok.*;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
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

    @OneToMany(mappedBy = "assignmentMaterialPathId.assignment")
    private List<AssignmentMaterialPath> materialPaths;

    @OneToMany(mappedBy = "studentAssignmentAnswerId.assignment")
    private List<StudentAssignmentAnswer> answers;

}
