package com.centralstudenthub.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(name = "student_contact")
public class StudentContact {

    @Id
    @Column(nullable = false, updatable = false)
    private String label;

    @Column
    private String data;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "studentId", nullable = false)
    private StudentProfile student;

}
