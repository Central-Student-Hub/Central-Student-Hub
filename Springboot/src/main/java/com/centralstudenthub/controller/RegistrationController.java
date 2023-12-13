package com.centralstudenthub.controller;

import com.centralstudenthub.entity.student_profile.course.semester_courses.SemesterCourse;
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
    public ResponseEntity<Boolean> addCourseToCart(@RequestParam int studentId,@RequestParam int courseId){
        boolean response = registrationService.addCourseToCart(studentId,courseId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/checkOut")
    public ResponseEntity<Boolean> checkOut(@RequestParam int studentId,@RequestParam String courseIds){
        boolean response = registrationService.checkOut(studentId,courseIds);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/getAvailableCourses")
    public ResponseEntity<List<SemesterCourse>> getAvailableCourses(@RequestParam int studentId){
        List<SemesterCourse> semesterCourses = registrationService.getAvailableCourses(studentId);
        return ResponseEntity.ok(semesterCourses);
    }

    @PostMapping("/unregisterCourse")
    public ResponseEntity<Boolean> unregisterCourse(@RequestParam int studentId,@RequestParam int courseId){
        boolean response = registrationService.unregisterCourse(studentId,courseId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/searchForCourse")
    public ResponseEntity<List<SemesterCourse>> searchForCourse(@RequestParam String searchPhrase){
        List<SemesterCourse> semesterCourses = registrationService.searchForCourse(searchPhrase);
        return ResponseEntity.ok(semesterCourses);
    }

    @GetMapping("/showFees")
    public ResponseEntity<Float> showFees(@RequestParam int studentId){
        float fees = registrationService.showFees(studentId);
        return ResponseEntity.ok(fees);
    }

    @GetMapping("/getPaymentDeadLine")
    public ResponseEntity<Date> getPaymentDeadLine(){
        Date date = registrationService.getPaymentDeadLine();
        return ResponseEntity.ok(date);
    }


}
