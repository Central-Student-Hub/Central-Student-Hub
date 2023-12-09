package com.centralstudenthub.service;

import com.centralstudenthub.Model.StudentInfo;
import com.centralstudenthub.Model.TeachingStaffInfo;
import com.centralstudenthub.Model.WarningRequest;
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
    private OfficeHourRepository officeHourRepository;
    @Autowired
    private TeachingStaffContactRepository teachingStaffContactRepository;
    @Autowired
    private WarningRepository warningRepository;

    public void updateTeachingStaffData(TeachingStaffInfo request) {
        TeachingStaffProfile teacher = new TeachingStaffProfile();
        teacher.setTeacherId(request.getTeacherId());
        teacher.setFirstName(request.getFirstName());
        teacher.setLastName(request.getLastName());
        teacher.setBiography(request.getBiography());
        teacher.setProfilePictureUrl(request.getProfilePictureUrl());
        teacher.setDepartment(request.getDepartment());
        teacher.setTeacherOfficeHours(request.getTeacherOfficeHours());
        teacher.setTeacherTeachingStaffContacts(request.getTeacherTeachingStaffContacts());
        teachingStaffProfileRepository.save(teacher);
    }

    public void updateStudentData(StudentInfo request){
        StudentProfile student = new StudentProfile();
        student.setStudentId(request.getStudentId());
        student.setFirstName(request.getFirstName());
        student.setLastName(request.getLastName());
        student.setBiography(request.getBiography());
        student.setProfilePictureUrl(request.getProfilePictureUrl());
        student.setMajor(request.getMajor());
        student.setMinor(request.getMinor());
        student.setLevel(request.getLevel());
        student.setNoOfHours(request.getNoOfHours());
        student.setGpa(request.getGpa());
        student.setStudentStudentContacts(request.getStudentStudentContacts());
        student.setStudentWarnings(request.getStudentWarnings());
        student.setStudentStudentSemesterCourseGrades(request.getStudentSemesterCourseGrades());
        student.setStudentAssignmentAnswers(request.getStudentAssignmentAnswers());
        student.setStudentRegistrations(request.getStudentRegistrations());
        studentProfileRepository.save(student);
    }

    public TeachingStaffInfo getTeachingStaffInfo(Integer id) {
        Optional<TeachingStaffProfile> DBUser = teachingStaffProfileRepository.findById(id);
        if(DBUser.isPresent()) {
            TeachingStaffInfo teacher = new TeachingStaffInfo();
            teacher.setTeacherId(DBUser.get().getTeacherId());
            teacher.setFirstName(DBUser.get().getFirstName());
            teacher.setLastName(DBUser.get().getLastName());
            teacher.setBiography(DBUser.get().getBiography());
            teacher.setProfilePictureUrl(DBUser.get().getProfilePictureUrl());
            teacher.setDepartment(DBUser.get().getDepartment());
            teacher.setTeacherOfficeHours(DBUser.get().getTeacherOfficeHours());
            teacher.setTeacherTeachingStaffContacts(DBUser.get().getTeacherTeachingStaffContacts());
            return teacher;
        }
        return null;
    }

    public StudentInfo getStudentInfo(Integer id) {
        Optional<StudentProfile> DBUser = studentProfileRepository.findById(id);
        if(DBUser.isPresent()) {
            StudentInfo student = new StudentInfo();
            student.setStudentId(DBUser.get().getStudentId());
            student.setFirstName(DBUser.get().getFirstName());
            student.setLastName(DBUser.get().getLastName());
            student.setBiography(DBUser.get().getBiography());
            student.setProfilePictureUrl(DBUser.get().getProfilePictureUrl());
            student.setMajor(DBUser.get().getMajor());
            student.setMinor(DBUser.get().getMinor());
            student.setLevel(DBUser.get().getLevel());
            student.setNoOfHours(DBUser.get().getNoOfHours());
            student.setGpa(DBUser.get().getGpa());
            student.setStudentStudentContacts(DBUser.get().getStudentStudentContacts());
            student.setStudentWarnings(DBUser.get().getStudentWarnings());
            student.setStudentSemesterCourseGrades(DBUser.get().getStudentStudentSemesterCourseGrades());
            student.setStudentAssignmentAnswers(DBUser.get().getStudentAssignmentAnswers());
            student.setStudentRegistrations(DBUser.get().getStudentRegistrations());
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
