package com.centralstudenthub.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "student_contact")
public class StudentContact {

    @Id
    @Column(nullable = false, updatable = false)
    private String label;

    private String data;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "studentId", nullable = false)
    private StudentProfile student;
}
