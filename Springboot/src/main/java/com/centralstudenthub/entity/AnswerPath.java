package com.centralstudenthub.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(name = "answer_path")
public class AnswerPath {

    @Id
    @Column(nullable = false, updatable = false)
    private String answerPath;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assignmentAnsId", nullable = false)
    private AssignmentAnswer assignmentAns;

}
