package com.centralstudenthub.service;

import com.centralstudenthub.Model.Request.AnnouncementRequest;
import com.centralstudenthub.Model.StudentCourseResponses.StudentAnnouncementRes;
import com.centralstudenthub.entity.student_profile.course.semester_courses.Announcement;
import com.centralstudenthub.entity.student_profile.course.semester_courses.SemesterCourse;
import com.centralstudenthub.repository.AnnouncementRepository;
import com.centralstudenthub.repository.SemesterCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnnouncementService {

    @Autowired
    private SemesterCourseRepository semCourseRepository;
    @Autowired
    private AnnouncementRepository announcementRepository;
    public boolean addAnnouncement(AnnouncementRequest announcementRequest) {

        Optional<SemesterCourse> course = semCourseRepository.findById(announcementRequest.getSemCourseId());

        if(course.isPresent()){
            Announcement announcement = Announcement.builder()
                    .announcementName(announcementRequest.getAnnouncementName())
                    .description(announcementRequest.getDescription())
                    .semCourse(course.get())
                    .build();

            announcementRepository.save(announcement);
            return true;
        }
        else{
            return false;
        }
    }

    public Announcement getAnnouncement(Long id) {

        Optional<Announcement> announcement = announcementRepository.findById(id);
        return announcement.orElse(null);
    }

    public List<StudentAnnouncementRes> getAnnouncementBySemCourseId(Long semCourseId) {
        Optional<SemesterCourse> course = semCourseRepository.findById(semCourseId);
        if(course.isEmpty())return null;

        List<Announcement> announcements = course.get().getAnnouncements();
        return announcements.stream().map( announcement -> StudentAnnouncementRes.builder()
                .announcementId(announcement.getAnnouncementId())
                .announcementName(announcement.getAnnouncementName())
                .description(announcement.getDescription())
                .build()).toList();
    }
}
