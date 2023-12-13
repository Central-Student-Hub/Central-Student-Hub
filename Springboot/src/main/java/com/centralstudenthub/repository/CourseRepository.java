package com.centralstudenthub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.centralstudenthub.entity.student_profile.course.Course;

public interface CourseRepository extends JpaRepository<Course, Integer> {
    boolean existsByCode(String code);
}
