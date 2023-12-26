package com.centralstudenthub.repository;

import com.centralstudenthub.entity.student_profile.course.semester_courses.assignments.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment,Long> {
}
