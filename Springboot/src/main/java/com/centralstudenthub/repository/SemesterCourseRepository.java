package com.centralstudenthub.repository;

import com.centralstudenthub.Model.Semester;
import com.centralstudenthub.entity.student_profile.course.semester_courses.SemesterCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SemesterCourseRepository extends JpaRepository<SemesterCourse,Long> {
    List<SemesterCourse> findBySemester(Semester semester);

    @Query("select sc from SemesterCourse sc where sc.course.courseId = :courseId")
    List<SemesterCourse> findAllByCourseId(Integer courseId);

    @Query(value = "select c.code from semester_course as sc, course as c where c.courseId = sc.courseId and sc.semCourseId = :semCourseId", nativeQuery = true)
    String findCourseCodeBySemesterCourseId(Long semCourseId);

    @Query(value = "select c.name from semester_course as sc, course as c where c.courseId = sc.courseId and sc.semCourseId = :semCourseId", nativeQuery = true)
    String findCourseNameBySemesterCourseId(Long semCourseId);

    @Query(value = "select c.creditHours from semester_course as sc, course as c where c.courseId = sc.courseId and sc.semCourseId = :semCourseId", nativeQuery = true)
    int findCreditHoursBySemesterCourseId(Long semCourseId);

    @Query(value = "select c.description from semester_course as sc, course as c where c.courseId = sc.courseId and sc.semCourseId = :semCourseId", nativeQuery = true)
    String findCourseDescriptionBySemesterCourseId(Long semCourseId);
}
