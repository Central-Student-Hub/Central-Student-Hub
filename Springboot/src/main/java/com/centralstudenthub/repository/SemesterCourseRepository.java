package com.centralstudenthub.repository;

import com.centralstudenthub.entity.SemesterCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SemesterCourseRepository extends JpaRepository<SemesterCourse,Long> {
}

