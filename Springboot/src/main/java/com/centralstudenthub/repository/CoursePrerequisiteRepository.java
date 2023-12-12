package com.centralstudenthub.repository;

import com.centralstudenthub.entity.student_profile.course.course_prerequisites.CoursePrerequisite;
import com.centralstudenthub.entity.student_profile.course.course_prerequisites.CoursePrerequisiteId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoursePrerequisiteRepository extends JpaRepository<CoursePrerequisite, CoursePrerequisiteId> {
}
