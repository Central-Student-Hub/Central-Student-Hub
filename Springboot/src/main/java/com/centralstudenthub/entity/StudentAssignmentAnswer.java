package com.centralstudenthub.entity;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(name = "student_assignment_answer")
public class StudentAssignmentAnswer {

    private String answerPath;
    private Double grade;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "studentId", nullable = false)
    private StudentProfile student;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assignmentId", nullable = false)
    private Assignment assignment;

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
