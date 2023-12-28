package com.centralstudenthub.service;

import com.centralstudenthub.Model.Request.BatchGradeRequest;
import com.centralstudenthub.Model.Request.ExamRequest;
import com.centralstudenthub.Model.Request.StudentProfileRequest;
import com.centralstudenthub.Model.Response.ExamResponse;
import com.centralstudenthub.entity.exam.Exam;
import com.centralstudenthub.entity.exam.ExamId;
import com.centralstudenthub.exception.AlreadyExistsException;
import com.centralstudenthub.exception.NotFoundException;
import com.centralstudenthub.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import com.centralstudenthub.entity.sessions.location.Location;

@Service
public class ExamService {
    @Autowired
    private ExamRepository examRepository;
    @Autowired
    private RegistrationRepository registrationRepository;
    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private SemesterCourseService semesterCourseService;
    @Autowired
    private SemesterCourseRepository semesterCourseRepository;
    @Autowired
    private StudentProfileRepository studentProfileRepository;
    @Autowired
    private StudentCourseGradeService studentCourseGradeService;
    @Autowired
    private UserProfileService userProfileService;

    public List<ExamResponse> getExamsList(Integer id) throws NotFoundException {
        List<ExamResponse> ret = new ArrayList<>();
        List<Exam> exams = examRepository.findAllByStudentId(id);
        for (var exam : exams) {
            var semesterCourse = semesterCourseRepository.findById(exam.getId().getSemCourseId());
            ExamResponse response = ExamResponse.builder()
                    .courseName(semesterCourseService.getSemesterCourseResponse(semesterCourse.get()).getName())
                    .seatNumber(exam.getSeatNumber())
                    .building(exam.getLocationId().getBuilding())
                    .room(exam.getLocationId().getRoom())
                    .date(exam.getDate())
                    .fromTime(exam.getFromTime())
                    .period(exam.getPeriod())
                    .build();
            ret.add(response);
        }
        return ret;
    }

    public boolean distributeStudents(ExamRequest request) {
        var students = registrationRepository.findAllStudentBySemCourseId(request.getSemCourseId());
        List<Location> locations = locationRepository.findAll();
        int curLocation = 0 , seatNumber = 0 , curCapacity = 0;
        for (var studentId : students) {
            while (curLocation < locations.size() && curCapacity > locations.get(curLocation).getCapacity()) {
                curCapacity = 0;
                curLocation++;
            }
            if (curLocation >= locations.size())
                return false;
            ++curCapacity;
            var semCourse = semesterCourseRepository.findById(request.getSemCourseId());
            if (semCourse.isEmpty())
                return false;
            var student = studentProfileRepository.findById(studentId);
            if (student.isEmpty())
                return false;
            Exam exam = Exam.builder()
                    .id(ExamId.builder().studentId(studentId).semCourseId(request.getSemCourseId()).build())
                    .student(student.get())
                    .semesterCourse(semCourse.get())
                    .seatNumber(++seatNumber)
                    .locationId(locations.get(curLocation).getId())
                    .date(request.getDate())
                    .fromTime(request.getFromTime())
                    .period(request.getPeriod())
                    .build();
            examRepository.save(exam);
        }
        return true;
    }

    public boolean batchGrade(BatchGradeRequest request) throws AlreadyExistsException, NotFoundException {
        boolean ret = true;
        Long semCourseId = request.getSemCourseId();
        List<Integer> studentIds = request.getStudentIds();
        List<Double> grades = request.getGrades();
        for (int i = 0 ; i < studentIds.size() ; ++i)
            ret &= gradeCourse(semCourseId , studentIds.get(i) , grades.get(i));
        return ret;
    }

    private boolean gradeCourse(Long semCourseId , Integer studentId , double grade)
            throws AlreadyExistsException, NotFoundException {
        var courseId = semesterCourseRepository.findCourseIdBySemesterCourseId(semCourseId);
        boolean AddCheck = studentCourseGradeService.addStudentCourseGrade(courseId , studentId , grade);
        if (!AddCheck)
            return false;
        var student = studentProfileRepository.findById(studentId);
        if (student.isEmpty())
            return false;
        student.get().setGpa(studentCourseGradeService.calculateGPA(studentId));
        studentProfileRepository.save(student.get());
        return true;
    }

}
