package com.centralstudenthub.repository;
import com.centralstudenthub.entity.student_profile.Warning;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WarningRepository extends JpaRepository<Warning, Integer> {
    @Query(value = "select * from warning where studentId = ?1", nativeQuery = true)
    List<Warning> findAllByStudentId(int studentId);
}
