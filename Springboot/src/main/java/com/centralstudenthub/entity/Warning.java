package com.centralstudenthub.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(name = "warning")
public class Warning {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer warningId;

    @Column
    private String reason;

    @Column
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "studentId", nullable = false)
    private StudentProfile student;

}
