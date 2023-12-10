package com.centralstudenthub.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "assignment_material_path")
public class AssignmentMaterialPath {

    @EmbeddedId
    AssignmentMaterialPathId assignmentMaterialPathId;
}

