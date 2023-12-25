package com.centralstudenthub.repository;

import com.centralstudenthub.entity.exam.Exam;
import com.centralstudenthub.entity.exam.ExamId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface ExamRepository extends JpaRepository<Exam, ExamId> {
    @Query(value = "SELECT * from Exam WHERE Exam.id.studentProfile.studentId = :id")
    List<Exam> findAllByStudentId(Integer id);
}
