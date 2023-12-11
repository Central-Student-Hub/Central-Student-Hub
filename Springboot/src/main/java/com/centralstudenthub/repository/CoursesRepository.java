package com.centralstudenthub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.centralstudenthub.entity.Course;

public interface CoursesRepository extends JpaRepository<Course, Integer> {
}
