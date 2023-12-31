package com.centralstudenthub.entity.student_profile;

import com.centralstudenthub.Model.Request.WarningModel;
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

    public WarningModel modelFromWarning() {
        return WarningModel.builder()
                .warningId(warningId)
                .reason(reason)
                .date(date)
                .build();
    }
}
