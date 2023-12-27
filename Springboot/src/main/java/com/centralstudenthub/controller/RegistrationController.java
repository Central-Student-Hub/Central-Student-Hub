package com.centralstudenthub.controller;

import com.centralstudenthub.Model.Request.AddCourseToCartRequest;
import com.centralstudenthub.Model.Request.StudentProfileRequest;
import com.centralstudenthub.Model.Response.student_profile.course.semester_courses.SemesterCourseResponse;
import com.centralstudenthub.exception.NullCourseException;
import com.centralstudenthub.exception.NullRegisteredSessionsException;
import com.centralstudenthub.exception.NullSemesterCourseException;
import com.centralstudenthub.exception.NullStudentProfileException;
import com.centralstudenthub.service.JwtService;
import com.centralstudenthub.service.RegistrationService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(value = "http://localhost:3000", allowCredentials = "true", allowedHeaders = "*")
@RequestMapping("/Register")
public class RegistrationController {
    private final RegistrationService registrationService;
    private final JwtService jwtService;

    @Autowired
    public RegistrationController(RegistrationService registrationService, JwtService jwtService) {
        this.registrationService = registrationService;
        this.jwtService = jwtService;
    }

    @PostMapping("/addCourseToCart")
    public ResponseEntity<Boolean> addCourseToCart(@RequestBody AddCourseToCartRequest addCourseToCartRequest, HttpServletRequest request)
            throws NullStudentProfileException, NullCourseException, NullRegisteredSessionsException, NullSemesterCourseException {
        int studentId = jwtService.extractId(jwtService.token(request));
        boolean response = registrationService.addCourseToCart(addCourseToCartRequest, studentId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/checkOut")
    public ResponseEntity<Boolean> checkOut(HttpServletRequest request, @RequestParam List<Long> semesterCourseIds) {
        int studentId = jwtService.extractId(jwtService.token(request));
        boolean response = registrationService.checkOut(studentId, semesterCourseIds);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getAvailableCourses")
    public ResponseEntity<List<SemesterCourseResponse>> getAvailableCourses(HttpServletRequest request, @RequestParam String searchPhrase) {
        int studentId = jwtService.extractId(jwtService.token(request));
        List<SemesterCourseResponse> semesterCourses = registrationService.getAvailableCourses(studentId, searchPhrase);
        return ResponseEntity.ok(semesterCourses);
    }

    @GetMapping("/showFees")
    public ResponseEntity<Double> showFees(HttpServletRequest request) {
        int studentId = jwtService.extractId(jwtService.token(request));
        double fees = registrationService.showFees(studentId);
        return ResponseEntity.ok(fees);
    }

    @GetMapping("/getPaymentDeadLine")
    public ResponseEntity<Date> getPaymentDeadLine(HttpServletRequest request) {
        int studentId = jwtService.extractId(jwtService.token(request));
        Date date = registrationService.getPaymentDeadLine(studentId);
        return ResponseEntity.ok(date);
    }

    @PostMapping("/setPaymentDeadline")
    public ResponseEntity<Boolean> getPaymentDeadLine(@RequestBody Date date) {
        return ResponseEntity.ok(registrationService.setPaymentDeadLine(date));
    }

    @GetMapping("/getStudentsBySemesterCourse/{semesterCourseId}")
    public ResponseEntity<List<StudentProfileRequest>> getStudentsBySemesterCourse(@PathVariable Long semesterCourseId) {
        return ResponseEntity.ok(registrationService.getStudentsBySemesterCourse(semesterCourseId));
    }
}
