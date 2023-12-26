package com.centralstudenthub.service;

import com.centralstudenthub.entity.student_profile.course.semester_courses.Feedback;
import com.centralstudenthub.entity.student_profile.course.semester_courses.SemesterCourse;
import com.centralstudenthub.exception.NotFoundException;
import com.centralstudenthub.repository.FeedbackRepository;
import com.centralstudenthub.repository.SemesterCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class FeedbackService {
    private final SemesterCourseRepository semesterCourseRepository;
    private final FeedbackRepository feedbackRepository;

    @Autowired
    public FeedbackService(SemesterCourseRepository semesterCourseRepository, FeedbackRepository feedbackRepository) {
        this.semesterCourseRepository = semesterCourseRepository;
        this.feedbackRepository = feedbackRepository;
    }

    public Long addFeedback(Long id, String content) throws NotFoundException {
        Optional<SemesterCourse> semesterCourse = semesterCourseRepository.findById(id);
        if (semesterCourse.isEmpty())
            throw new NotFoundException("Semester Course not found");

        Feedback feedback = Feedback.builder().semCourse(semesterCourse.get()).content(content).build();
        feedbackRepository.save(feedback);
        return feedback.getFeedbackId();
    }

    public String getFeedback(Long feedbackId) throws NotFoundException {
        Optional<Feedback> feedback = feedbackRepository.findById(feedbackId);
        if (feedback.isEmpty())
            throw new NotFoundException("Feedback not found");

        return feedback.get().getContent();
    }

    public List<String> getFeedbacks(Long id) throws NotFoundException {
        Optional<SemesterCourse> semesterCourse = semesterCourseRepository.findById(id);
        if (semesterCourse.isEmpty())
            throw new NotFoundException("Semester Course not found");

        return feedbackRepository.findAllFeedbacksBySemCourseId(id);
    }

    public boolean updateFeedback(Long feedbackId, String newContent) throws NotFoundException {
        if (newContent == null) return false;
        Optional<Feedback> feedback = feedbackRepository.findById(feedbackId);
        if (feedback.isEmpty())
            throw new NotFoundException("Feedback not found");

        feedback.get().setContent(newContent);
        feedbackRepository.save(feedback.get());
        return true;
    }

    public boolean deleteFeedback(Long feedbackId) throws NotFoundException {
        Optional<Feedback> feedback = feedbackRepository.findById(feedbackId);
        if (feedback.isEmpty())
            throw new NotFoundException("Feedback not found");

        feedbackRepository.delete(feedback.get());
        return true;
    }
}
