package com.centralstudenthub.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "course_material_path")
public class CourseMaterialPath {

    @EmbeddedId
    private CourseMaterialPathId id;
}
