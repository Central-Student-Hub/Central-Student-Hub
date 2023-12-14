package com.centralstudenthub.controller;

import com.centralstudenthub.Model.Request.StudentProfileRequest;
import com.centralstudenthub.Model.Request.TeachingStaffProfileRequest;
import com.centralstudenthub.Model.Request.WarningRequest;
import com.centralstudenthub.entity.student_profile.StudentProfile;
import com.centralstudenthub.entity.teacher_profile.OfficeHour;
import com.centralstudenthub.entity.teacher_profile.TeachingStaffProfile;
import com.centralstudenthub.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/Profile")
public class UserProfileController {
    @Autowired
    private UserProfileService userProfileService;

    @PostMapping("/updateTeacherProfile")
    public void updateTeachingStaffData(@RequestBody TeachingStaffProfileRequest request) {
        userProfileService.updateTeachingStaffData(request);
    }

    @PostMapping("/updateStudentProfile")
    public void updateStudentData(@RequestBody StudentProfileRequest request) {
        userProfileService.updateStudentData(request);
    }

    @GetMapping("/TeacherProfile/{id}")
    public TeachingStaffProfile getTeachingStaffProfileInfo(@PathVariable("id") Integer id) {
        return userProfileService.getTeachingStaffProfileInfo(id);
    }

    @GetMapping("/StudentProfile/{id}")
    public StudentProfile getStudentProfileInfo(@PathVariable("id") Integer id) {
        return userProfileService.getStudentProfileInfo(id);
    }

    @GetMapping("/OfficeHour/{id}")
    public List<OfficeHour> getOfficeHour(@PathVariable("id") Integer id) {
        return userProfileService.getOfficeHour(id);
    }

    @PostMapping("/addWarning/{id}")
    public Integer addWarning(@PathVariable("id") Integer id , @RequestBody WarningRequest request) {
        return userProfileService.addWarning(id , request);
    }
}
