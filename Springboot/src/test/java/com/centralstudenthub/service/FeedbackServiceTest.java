package com.centralstudenthub.service;

import com.centralstudenthub.entity.student_profile.course.semester_courses.Feedback;
import com.centralstudenthub.entity.student_profile.course.semester_courses.SemesterCourse;
import com.centralstudenthub.exception.NotFoundException;
import com.centralstudenthub.repository.FeedbackRepository;
import com.centralstudenthub.repository.SemesterCourseRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FeedbackServiceTest {
    @Autowired
    private FeedbackService feedbackService;

    @MockBean
    private SemesterCourseRepository semesterCourseRepository;

    @MockBean
    private FeedbackRepository feedbackRepository;

    @Test
    public void addFeedback_semCourseId_NotInDB() {
        Mockito.when(semesterCourseRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> feedbackService.addFeedback(1L, "content"));
    }

    @Test
    public void addFeedback_semCourseId_InDB() throws NotFoundException {
        SemesterCourse semesterCourse = SemesterCourse.builder().semCourseId(1L).build();
        Feedback feedback = Feedback.builder().feedbackId(2L).semCourse(semesterCourse).content("content").build();

        Mockito.when(semesterCourseRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(semesterCourse));
        Mockito.when(feedbackRepository.save(Mockito.any(Feedback.class))).then(feedback1 -> {
            Feedback feedback2 = feedback1.getArgument(0);
            feedback2.setFeedbackId(feedback.getFeedbackId());
            return feedback2;
        });
        assertEquals(feedback.getFeedbackId(), feedbackService.addFeedback(semesterCourse.getSemCourseId(), feedback.getContent()));
    }

    @Test
    public void getFeedback_feedbackId_NotInDB() {
        Mockito.when(feedbackRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> feedbackService.getFeedback(1L));
    }

    @Test
    public void getFeedback_feedbackId_InDB() throws NotFoundException {
        Feedback feedback = Feedback.builder().feedbackId(1L).content("content").build();
        Mockito.when(feedbackRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(feedback));
        assertEquals(feedback.getContent(), feedbackService.getFeedback(feedback.getFeedbackId()));
    }

    @Test
    public void getFeedbacks_semCourseId_NotInDB() {
        Mockito.when(semesterCourseRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> feedbackService.getFeedbacks(1L));
    }

    @Test
    public void getFeedbacks_semCourseId_InDB() throws NotFoundException {
        SemesterCourse semesterCourse = SemesterCourse.builder().semCourseId(1L).build();
        List<String> feedbacksContents = List.of("content1", "content2", "content3");

        Mockito.when(semesterCourseRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(semesterCourse));
        Mockito.when(feedbackRepository.findAllFeedbacksBySemCourseId(semesterCourse.getSemCourseId())).thenReturn(feedbacksContents);

        assertEquals(feedbacksContents, feedbackService.getFeedbacks(semesterCourse.getSemCourseId()));
    }

    @Test
    public void updateFeedback_newContent_Null() throws NotFoundException {
        assertFalse(feedbackService.updateFeedback(1L, null));
    }

    @Test
    public void updateFeedback_feedbackId_NotInDB() {
        Mockito.when(feedbackRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> feedbackService.updateFeedback(1L, "newContent"));
    }

    @Test
    public void updateFeedback_feedbackId_InDB_newContent_NotNULL() throws NotFoundException {
        Feedback feedback = Feedback.builder().feedbackId(1L).content("content").build();

        Mockito.when(feedbackRepository.findById(feedback.getFeedbackId())).thenReturn(Optional.of(feedback));
        Mockito.when(feedbackRepository.save(feedback)).thenReturn(null);

        assertTrue(feedbackService.updateFeedback(feedback.getFeedbackId(), "newContent"));
    }

    @Test
    public void deleteFeedback_feedbackId_NotInDB() {
        Mockito.when(feedbackRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> feedbackService.deleteFeedback(1L));
    }

    @Test
    public void deleteFeedback_feedbackId_InDB() throws NotFoundException {
        Feedback feedback = Feedback.builder().feedbackId(1L).content("content").build();

        Mockito.when(feedbackRepository.findById(feedback.getFeedbackId())).thenReturn(Optional.of(feedback));
        Mockito.doNothing().when(feedbackRepository).delete(feedback);

        assertTrue(feedbackService.deleteFeedback(feedback.getFeedbackId()));
    }
}