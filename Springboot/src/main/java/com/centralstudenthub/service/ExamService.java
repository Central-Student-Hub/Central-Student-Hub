package com.centralstudenthub.service;

import com.centralstudenthub.Model.Request.ExamRequest;
import com.centralstudenthub.Model.Response.ExamResponse;
import com.centralstudenthub.entity.exam.Exam;
import com.centralstudenthub.entity.exam.ExamId;
import com.centralstudenthub.exception.NotFoundException;
import com.centralstudenthub.repository.ExamRepository;
import com.centralstudenthub.repository.LocationRepository;
import com.centralstudenthub.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

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

    public List<ExamResponse> getExamsList(Integer id) throws NotFoundException {
        List<ExamResponse> ret = new ArrayList<>();
        List<Exam> exams = examRepository.findAllByStudentId(id);
        for (var exam : exams) {
            ExamResponse response = ExamResponse.builder()
                    .courseName(semesterCourseService.getSemesterCourse(exam.getId().getSemCourseId()).getName())
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
        var locations = locationRepository.findAll();
        int curLocation = 0 , seatNumber = 0 , curCapacity = 0;
        for (var student : students) {
            while (curLocation < locations.size() && curCapacity > locations.get(curLocation).getCapacity()) {
                curCapacity = 0;
                curLocation++;
            }
            if (curLocation >= locations.size())
                return false;
            ++curCapacity;
            Exam exam = Exam.builder()
                    .id(ExamId.builder().studentId(student).semCourseId(request.getSemCourseId()).build())
                    .seatNumber(++seatNumber)
                    .locationId(locations.get(curLocation).getId())
                    .date(request.getDate())
                    .period(request.getPeriod())
                    .build();
            examRepository.save(exam);
        }
        return true;
    }

}
