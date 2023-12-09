package com.centralstudenthub.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(name = "assignment_material_path")
public class AssignmentMaterialPath {

    @Id
    @Column(nullable = false, updatable = false)
    private String materialPath;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assignmentId", nullable = false)
    private Assignment assignment;

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
