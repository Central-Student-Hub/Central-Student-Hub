package com.centralstudenthub.service;

import com.centralstudenthub.entity.exam.Exam;
import com.centralstudenthub.repository.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamService {
    @Autowired
    private ExamRepository examRepository;

    public List<Exam> getExamsList(Integer id) {
        return examRepository.findAllByStudentId(id);
    }
}
