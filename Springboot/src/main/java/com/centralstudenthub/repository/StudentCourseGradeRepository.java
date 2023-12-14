package com.centralstudenthub.repository;

import com.centralstudenthub.entity.student_profile.course.student_course_grades.StudentCourseGrade;
import com.centralstudenthub.entity.student_profile.course.student_course_grades.StudentCourseGradeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentCourseGradeRepository extends JpaRepository<StudentCourseGrade, StudentCourseGradeId> {

    @Query("SELECT scg.id.course.courseId, scg.studentGrade FROM StudentCourseGrade scg " +
            "WHERE scg.id.student.studentId = :studentId")
    List<Object[]> findAllStudentCoursesGradesByStudentId(Integer studentId);

    @Query("SELECT scg.id.student.studentId, scg.studentGrade FROM StudentCourseGrade scg " +
            "WHERE scg.id.course.courseId = :courseId")
    List<Object[]> findAllCourseStudentsGradesByCourseId(Integer courseId);
}
