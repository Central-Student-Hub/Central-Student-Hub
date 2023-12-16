package com.centralstudenthub.entity.student_profile.course.semester_courses.assignments.assignment_material_paths;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "assignment_material_path")
public class AssignmentMaterialPath {

    @EmbeddedId
    AssignmentMaterialPathId assignmentMaterialPathId;
}

