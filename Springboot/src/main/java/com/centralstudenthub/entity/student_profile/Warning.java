package com.centralstudenthub.entity.student_profile;

import com.centralstudenthub.Model.Response.student_profile.WarningResponse;
import jakarta.persistence.*;

import java.time.LocalDate;

import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "warning")
public class Warning {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer warningId;

    private String reason;
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "studentId", nullable = false)
    private StudentProfile student;

    public WarningResponse toResponse() {
        return WarningResponse.builder()
                .warningId(warningId)
                .reason(reason)
                .date(date)
                .build();
    }
}
