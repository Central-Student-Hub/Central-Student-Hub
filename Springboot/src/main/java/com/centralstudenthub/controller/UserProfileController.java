package com.centralstudenthub.controller;

import com.centralstudenthub.Model.Request.StudentProfileRequest;
import com.centralstudenthub.Model.Response.student_profile.course.StudentCourseGradeResponse;
import com.centralstudenthub.Model.Response.teacher_profile.TeachingStaffProfileModel;
import com.centralstudenthub.Model.Request.WarningRequest;
import com.centralstudenthub.entity.teacher_profile.OfficeHour;
import com.centralstudenthub.exception.NotFoundException;
import com.centralstudenthub.service.JwtService;
import com.centralstudenthub.service.StudentCourseGradeService;
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
    private final UserProfileService userProfileService;
    private final JwtService jwtService;
    private final StudentCourseGradeService studentCourseGradeService;

    @Autowired
    public UserProfileController(UserProfileService userProfileService, JwtService jwtService, StudentCourseGradeService studentCourseGradeService) {
        this.userProfileService = userProfileService;
        this.jwtService = jwtService;
        this.studentCourseGradeService = studentCourseGradeService;
    }

    @PutMapping("/updateTeacherProfile")
    public void updateTeachingStaffData(@RequestBody TeachingStaffProfileModel request, HttpServletRequest httpServletRequest) {
        int id = jwtService.extractId(jwtService.token(httpServletRequest));
        userProfileService.updateTeachingStaffData(id,request);
    }

    @PutMapping("/updateStudentProfile/{id}")
    public ResponseEntity<Boolean> updateStudentData(@RequestBody StudentProfileRequest request, @PathVariable int id) {
        boolean res = userProfileService.updateStudentData(id, request);
        return ResponseEntity.ok(res);
    }

    @PutMapping("/updateStudentProfile")
    public ResponseEntity<Boolean> updateStudentData(@RequestBody StudentProfileRequest request,HttpServletRequest httpServletRequest) {
        int id = jwtService.extractId(jwtService.token(httpServletRequest));
        boolean res = userProfileService.updateStudentData(id,request);
        return ResponseEntity.ok(res);
    }

    @GetMapping({"/getTeacherProfile/{id}", "/getTeacherProfile"})
    public ResponseEntity<TeachingStaffProfileModel> getTeachingStaffProfileInfo(
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

    @PostMapping("/addWarning")
    public boolean addWarning(@RequestBody WarningRequest request) {
        return userProfileService.addWarning(request);
    }

    @GetMapping("/teachingStaff")
    public List<TeachingStaffProfileModel> getAllTeachingStaff() {
        return userProfileService.getAllTeachingStaff();
    }

    @GetMapping("/students")
    public List<StudentProfileRequest> getAllStudents() {
        return userProfileService.getAllStudents();
    }

    @GetMapping("/grades")
    public List<StudentCourseGradeResponse> getGrades(HttpServletRequest request) throws NotFoundException {
        int id = jwtService.extractId(jwtService.token(request));
        return studentCourseGradeService.getStudentGrades(id);
    }
}
