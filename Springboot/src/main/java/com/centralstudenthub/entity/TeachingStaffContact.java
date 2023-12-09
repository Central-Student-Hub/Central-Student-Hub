package com.centralstudenthub.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
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

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
