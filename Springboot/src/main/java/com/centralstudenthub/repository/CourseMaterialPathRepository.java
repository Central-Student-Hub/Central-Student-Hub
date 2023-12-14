package com.centralstudenthub.repository;

import com.centralstudenthub.entity.student_profile.course.semester_courses.course_material_paths.CourseMaterialPath;
import com.centralstudenthub.entity.student_profile.course.semester_courses.course_material_paths.CourseMaterialPathId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseMaterialPathRepository extends JpaRepository<CourseMaterialPath, CourseMaterialPathId> {

    @Query("SELECT cmp.id.materialPath FROM CourseMaterialPath cmp WHERE cmp.id.semCourse.semCourseId = :id")
    List<String> findAllMaterialPathsBySemCourseId(Long id);
}
