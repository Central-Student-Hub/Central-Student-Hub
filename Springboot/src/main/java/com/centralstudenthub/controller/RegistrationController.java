package com.centralstudenthub.controller;

import com.centralstudenthub.Model.Request.AddCourseToCartRequest;
import com.centralstudenthub.entity.student_profile.course.semester_courses.SemesterCourse;
import com.centralstudenthub.exception.NullCourseException;
import com.centralstudenthub.exception.NullRegisteredSessionsException;
import com.centralstudenthub.exception.NullSemesterCourseException;
import com.centralstudenthub.exception.NullStudentProfileException;
import com.centralstudenthub.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/Register")
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @PostMapping("/addCourseToCart")
    public ResponseEntity<Boolean> addCourseToCart(@RequestBody AddCourseToCartRequest addCourseToCartRequest)
            throws NullStudentProfileException, NullCourseException, NullRegisteredSessionsException, NullSemesterCourseException {

        boolean response = registrationService.addCourseToCart(addCourseToCartRequest);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/checkOut")
    public ResponseEntity<Boolean> checkOut(@RequestParam int studentId,@RequestParam List<Long> semesterCourseIds){
        boolean response = registrationService.checkOut(studentId,semesterCourseIds);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/getAvailableCourses")
    public ResponseEntity<List<SemesterCourse>> getAvailableCourses(@RequestParam int studentId,@RequestParam String searchPhrase){
        List<SemesterCourse> semesterCourses = registrationService.getAvailableCourses(studentId,searchPhrase);
        return ResponseEntity.ok(semesterCourses);
    }

    @GetMapping("/showFees")
    public ResponseEntity<Double> showFees(@RequestParam int studentId){
        double fees = registrationService.showFees(studentId);
        return ResponseEntity.ok(fees);
    }

    @GetMapping("/getPaymentDeadLine")
    public ResponseEntity<Date> getPaymentDeadLine(@RequestParam int studentId){
        Date date = registrationService.getPaymentDeadLine(studentId);
        return ResponseEntity.ok(date);
    }


}
