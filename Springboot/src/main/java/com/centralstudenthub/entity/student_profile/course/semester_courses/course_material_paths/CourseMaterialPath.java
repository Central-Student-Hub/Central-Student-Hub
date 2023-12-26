package com.centralstudenthub.entity.student_profile.course.semester_courses.course_material_paths;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "course_material_path")
public class CourseMaterialPath {

    @EmbeddedId
    private CourseMaterialPathId id;
}
