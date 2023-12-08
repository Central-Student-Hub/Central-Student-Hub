package com.centralstudenthub.entity;

import jakarta.persistence.*;

import java.util.Set;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(name = "assignment_answer")
public class AssignmentAnswer {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long assignmentAnsId;

    @Column
    private Double grade;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "studentId", nullable = false)
    private StudentProfile student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assignmentId", nullable = false)
    private Assignment assignment;

    @OneToMany(mappedBy = "assignmentAns")
    private Set<AnswerPath> assignmentAnsAnswerPaths;

}
