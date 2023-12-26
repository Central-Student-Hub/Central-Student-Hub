package com.centralstudenthub.repository;

import com.centralstudenthub.entity.student_profile.course.semester_courses.assignments.assignment_material_paths.AssignmentMaterialPath;
import com.centralstudenthub.entity.student_profile.course.semester_courses.assignments.assignment_material_paths.AssignmentMaterialPathId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssignmentMaterialPathRepository
        extends JpaRepository<AssignmentMaterialPath,AssignmentMaterialPathId> {

}


