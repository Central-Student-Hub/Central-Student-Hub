package com.centralstudenthub.controller;

import com.centralstudenthub.Model.Request.StudentProfileRequest;
import com.centralstudenthub.Model.Request.TeachingStaffProfileReqAndRes;
import com.centralstudenthub.Model.Request.WarningRequest;
import com.centralstudenthub.entity.student_profile.StudentProfile;
import com.centralstudenthub.entity.teacher_profile.OfficeHour;
import com.centralstudenthub.entity.teacher_profile.TeachingStaffProfile;
import com.centralstudenthub.service.JwtService;
import com.centralstudenthub.service.UserProfileService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(value = "http://localhost:3000", allowCredentials = "true", allowedHeaders = "*")
@RequestMapping("/Profile")
public class UserProfileController {
    @Autowired
    private UserProfileService userProfileService;
    @Autowired
    private JwtService jwtService;

    @PutMapping("/updateTeacherProfile")
    public void updateTeachingStaffData(@RequestBody TeachingStaffProfileReqAndRes request, HttpServletRequest httpServletRequest) {
        int id = jwtService.extractId(jwtService.token(httpServletRequest));
        userProfileService.updateTeachingStaffData(id,request);
    }

    @PutMapping("/updateStudentProfile")
    public void updateStudentData(@RequestBody StudentProfileRequest request,HttpServletRequest httpServletRequest) {
        int id = jwtService.extractId(jwtService.token(httpServletRequest));
        userProfileService.updateStudentData(id,request);
    }

    @GetMapping({"/getTeacherProfile/{id}", "/getTeacherProfile"})
    public ResponseEntity<TeachingStaffProfileReqAndRes> getTeachingStaffProfileInfo(
            @PathVariable(value = "id", required = false) Integer id, HttpServletRequest request) {
        if (id == null)
            id = jwtService.extractId(jwtService.token(request));
        return ResponseEntity.ok(userProfileService.getTeachingStaffProfileInfo(id));
    }

    @GetMapping({"/getStudentProfile/{id}", "/getStudentProfile"})
    public StudentProfileRequest getStudentProfileInfo(@PathVariable(value = "id", required = false) Integer id, HttpServletRequest request) {
        if (id == null)
            id = jwtService.extractId(jwtService.token(request));
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
