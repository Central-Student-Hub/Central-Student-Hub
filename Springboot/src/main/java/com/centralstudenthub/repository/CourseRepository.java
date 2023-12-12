package com.centralstudenthub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.centralstudenthub.entity.student_profile.course.Course;

import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Integer> {
    Optional<Course> findByCode(String code);
}
