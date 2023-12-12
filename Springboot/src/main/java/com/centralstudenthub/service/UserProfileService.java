package com.centralstudenthub.service;

import com.centralstudenthub.Model.StudentInfo;
import com.centralstudenthub.Model.TeachingStaffInfo;
import com.centralstudenthub.Model.Request.WarningRequest;
import com.centralstudenthub.entity.StudentProfile;
import com.centralstudenthub.entity.TeachingStaffProfile;
import com.centralstudenthub.entity.Warning;
import com.centralstudenthub.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserProfileService {
    @Autowired
    private TeachingStaffProfileRepository teachingStaffProfileRepository;
    @Autowired
    StudentProfileRepository studentProfileRepository;
    @Autowired
    private WarningRepository warningRepository;

    public void updateTeachingStaffData(TeachingStaffInfo request) {
        TeachingStaffProfile teacher = TeachingStaffProfile.builder()
                .teacherId(request.getTeacherId())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .biography(request.getBiography())
                .profilePictureUrl(request.getProfilePictureUrl())
                .department(request.getDepartment())
                .officeHours(request.getTeacherOfficeHours())
                .contacts(request.getTeacherTeachingStaffContacts())
                .build();

        teachingStaffProfileRepository.save(teacher);
    }

    public void updateStudentData(StudentInfo request){
        StudentProfile student = StudentProfile.builder()
                .studentId(request.getStudentId())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .biography(request.getBiography())
                .profilePictureUrl(request.getProfilePictureUrl())
                .major(request.getMajor())
                .minor(request.getMinor())
                .level(request.getLevel())
                .noOfHours(request.getNoOfHours())
                .gpa(request.getGpa())
                .contacts(request.getStudentContacts())
                .warnings(request.getStudentWarnings())
                .grades(request.getStudentSemesterCourseGrades())
                .assignmentAnswers(request.getStudentAssignmentAnswers())
                .registrations(request.getStudentRegistrations())
                .build();

        studentProfileRepository.save(student);
    }

    public TeachingStaffInfo getTeachingStaffInfo(Integer id) {
        Optional<TeachingStaffProfile> DBUser = teachingStaffProfileRepository.findById(id);
        if(DBUser.isPresent()) {
            TeachingStaffInfo teacher = TeachingStaffInfo.builder()
                    .teacherId(DBUser.get().getTeacherId())
                    .firstName(DBUser.get().getFirstName())
                    .lastName(DBUser.get().getLastName())
                    .biography(DBUser.get().getBiography())
                    .profilePictureUrl(DBUser.get().getProfilePictureUrl())
                    .department(DBUser.get().getDepartment())
                    .teacherOfficeHours(DBUser.get().getOfficeHours())
                    .teacherTeachingStaffContacts(DBUser.get().getContacts())
                    .build();
            return teacher;
        }
        return null;
    }

    public StudentInfo getStudentInfo(Integer id) {
        Optional<StudentProfile> DBUser = studentProfileRepository.findById(id);
        if(DBUser.isPresent()) {
            StudentInfo student = StudentInfo.builder()
                    .studentId(DBUser.get().getStudentId())
                    .firstName(DBUser.get().getFirstName())
                    .lastName(DBUser.get().getLastName())
                    .biography(DBUser.get().getBiography())
                    .profilePictureUrl(DBUser.get().getProfilePictureUrl())
                    .major(DBUser.get().getMajor())
                    .minor(DBUser.get().getMinor())
                    .level(DBUser.get().getLevel())
                    .noOfHours(DBUser.get().getNoOfHours())
                    .gpa(DBUser.get().getGpa())
                    .studentContacts(DBUser.get().getContacts())
                    .studentWarnings(DBUser.get().getWarnings())
                    .studentSemesterCourseGrades(DBUser.get().getGrades())
                    .studentAssignmentAnswers(DBUser.get().getAssignmentAnswers())
                    .studentRegistrations(DBUser.get().getRegistrations())
                    .build();
            return student;
        }
        return null;
    }

    public Boolean addWarning(Integer id , WarningRequest request) {
        Optional<StudentProfile> DBUser = studentProfileRepository.findById(id);
        if(DBUser.isEmpty()) {
            return false;
        }
        Optional<Warning> DBUser2 = warningRepository.findById(request.getWarningId());
        if (DBUser2.isPresent()){
            return false;
        }
        Warning newWarning = Warning.builder().warningId(request.getWarningId()).reason(request.getReason())
                .date(request.getDate()).student(DBUser.get()).build();
        warningRepository.save(newWarning);
        return true;
    }
}
