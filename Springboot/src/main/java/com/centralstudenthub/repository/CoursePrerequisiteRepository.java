package com.centralstudenthub.repository;

import com.centralstudenthub.entity.student_profile.course.course_prerequisites.CoursePrerequisite;
import com.centralstudenthub.entity.student_profile.course.course_prerequisites.CoursePrerequisiteId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoursePrerequisiteRepository extends JpaRepository<CoursePrerequisite, CoursePrerequisiteId> {
    @Query("SELECT id.prerequisite.courseId FROM CoursePrerequisite WHERE id.course.courseId = :id")
    List<Integer> findAllPrerequisites(int id);
}
