package com.centralstudenthub.repository;

import com.centralstudenthub.entity.student_profile.course.semester_courses.SemesterCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SemesterCourseRepository extends JpaRepository<SemesterCourse,Long> {
}

