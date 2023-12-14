package com.centralstudenthub.entity.student_profile;

import jakarta.persistence.*;

import java.time.LocalDate;

import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "warning")
public class Warning {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer warningId;

    private String data;
    private String reason;
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "studentId", nullable = false)
    private StudentProfile student;

}
