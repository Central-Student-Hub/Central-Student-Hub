package com.centralstudenthub.repository;

import com.centralstudenthub.Model.Semester;
import com.centralstudenthub.entity.student_profile.course.semester_courses.SemesterCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SemesterCourseRepository extends JpaRepository<SemesterCourse,Long> {
    List<SemesterCourse> findBySemester(Semester semester);
}

