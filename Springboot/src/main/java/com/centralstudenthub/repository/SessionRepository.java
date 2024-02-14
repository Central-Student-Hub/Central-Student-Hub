package com.centralstudenthub.repository;

import com.centralstudenthub.entity.sessions.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {
    @Query("SELECT s.sessionId, s.period, s.weekDay, s.sessionType FROM Session s WHERE s.semCourse.semCourseId = :id")
    List<Object[]> findAllSessionsBySemCourseId(Long id);

    @Query(value = "SELECT teacherId FROM Session WHERE semCourseId =:semCourseId",nativeQuery = true)
    int findTeacherIdBySemCourseId(Long semCourseId);
}
