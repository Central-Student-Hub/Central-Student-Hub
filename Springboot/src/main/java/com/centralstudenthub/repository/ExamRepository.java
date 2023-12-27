package com.centralstudenthub.repository;

import com.centralstudenthub.entity.exam.Exam;
import com.centralstudenthub.entity.exam.ExamId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamRepository extends JpaRepository<Exam, ExamId> {
    @Query(value = "select * from exam as e where e.studentId = :studentId",nativeQuery = true)
    List<Exam> findAllByStudentId(Integer studentId);
}

