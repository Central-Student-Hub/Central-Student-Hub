package com.centralstudenthub.controller;


import com.centralstudenthub.Model.Request.AnnouncementRequest;
import com.centralstudenthub.Model.StudentCourseResponses.StudentAnnouncementRes;
import com.centralstudenthub.entity.student_profile.course.semester_courses.Announcement;
import com.centralstudenthub.service.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(value = "http://localhost:3000", allowCredentials = "true", allowedHeaders = {"Authorization"})
@RequestMapping("/Announcement")
public class AnnouncementController {
    private final AnnouncementService announcementService;

    @Autowired
    public AnnouncementController(AnnouncementService announcementService) {
        this.announcementService = announcementService;
    }

    @PostMapping("/addAnnouncement")
    public ResponseEntity<Boolean> addAnnouncement(@RequestBody AnnouncementRequest announcementRequest){
        boolean response  = announcementService.addAnnouncement(announcementRequest);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/getAnnouncement")
    public ResponseEntity<Announcement> getAnnouncement(@RequestParam Long id){
        Announcement response  = announcementService.getAnnouncement(id);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/getAnnouncementBySemCourseId")
    public ResponseEntity<List<StudentAnnouncementRes>> getAnnouncementBySemCourseId(@RequestParam Long semCourseId){
        List<StudentAnnouncementRes> response  = announcementService.getAnnouncementBySemCourseId(semCourseId);
        return ResponseEntity.ok().body(response);
    }

}
