package com.centralstudenthub.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "teaching_staff_contact")
public class TeachingStaffContact {

    @Id
    @Column(nullable = false, updatable = false)
    private String label;

    private String data;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacherId", nullable = false)
    private TeachingStaffProfile teacher;

}
