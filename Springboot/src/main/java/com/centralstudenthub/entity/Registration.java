package com.centralstudenthub.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(name = "registration")
public class Registration {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "studentId", nullable = false)
    private StudentProfile student;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "semCourseId", nullable = false)
    private SemesterCourse semCourse;

    @Column
    private LocalDate paymentDeadline;

    @Column
    private Double paymentFees;
}
