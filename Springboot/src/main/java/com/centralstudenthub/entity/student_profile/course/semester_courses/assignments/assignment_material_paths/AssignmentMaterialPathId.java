package com.centralstudenthub.entity.student_profile.course.semester_courses.assignments.assignment_material_paths;

import com.centralstudenthub.entity.student_profile.course.semester_courses.assignments.Assignment;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AssignmentMaterialPathId implements Serializable {

    @Column(nullable = false, updatable = false)
    private String materialPath;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assignmentId", nullable = false)
    private Assignment assignment;
}
