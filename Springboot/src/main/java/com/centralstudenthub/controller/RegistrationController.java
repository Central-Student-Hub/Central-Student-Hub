package com.centralstudenthub.controller;

import com.centralstudenthub.Model.Request.AddCourseToCartRequest;
import com.centralstudenthub.Model.Response.SemesterCourseResponse;
import com.centralstudenthub.entity.student_profile.course.semester_courses.SemesterCourse;
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

    @Autowired
    private RegistrationService registrationService;

    @Autowired
    private JwtService jwtService;

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
}
