package com.centralstudenthub.repository;

import com.centralstudenthub.entity.student_profile.course.semester_courses.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    @Query("SELECT f.content FROM Feedback f WHERE f.semCourse.semCourseId = :id")
    List<String> findAllFeedbacksBySemCourseId(Long id);
}
