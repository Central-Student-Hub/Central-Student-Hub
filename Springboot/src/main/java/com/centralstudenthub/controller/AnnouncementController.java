package com.centralstudenthub.controller;


import com.centralstudenthub.Model.Request.AnnouncementRequest;
import com.centralstudenthub.entity.Announcement;
import com.centralstudenthub.service.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/Announcement")
public class AnnouncementController {

    @Autowired
    private AnnouncementService announcementService;
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

}
