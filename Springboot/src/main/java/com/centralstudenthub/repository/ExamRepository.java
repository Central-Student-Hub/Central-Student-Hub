package com.centralstudenthub.repository;

import com.centralstudenthub.entity.exam.Exam;
import com.centralstudenthub.entity.exam.ExamId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ExamRepository extends JpaRepository<Exam, ExamId> {
    List<Exam> findAllByStudentId(Integer studentId);
}

