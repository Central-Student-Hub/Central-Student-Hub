package com.centralstudenthub.service;

import com.centralstudenthub.Model.Request.StudentProfileRequest;
import com.centralstudenthub.Model.Request.TeachingStaffProfileReqAndRes;
import com.centralstudenthub.Model.Request.WarningRequest;
import com.centralstudenthub.entity.student_profile.StudentProfile;
import com.centralstudenthub.entity.student_profile.Warning;
import com.centralstudenthub.entity.teacher_profile.OfficeHour;
import com.centralstudenthub.entity.teacher_profile.TeachingStaffProfile;
import com.centralstudenthub.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserProfileService {
    @Autowired
    private TeachingStaffProfileRepository teachingStaffProfileRepository;
    @Autowired
    private StudentProfileRepository studentProfileRepository;
    @Autowired
    private OfficeHourRepository officeHourRepository;
    @Autowired
    private TeachingStaffContactRepository teachingStaffContactRepository;
    @Autowired
    private WarningRepository warningRepository;
    @Autowired
    private StudentContactRepository studentContactRepository;

    public void updateTeachingStaffData(int id, TeachingStaffProfileReqAndRes request) {

        Optional<TeachingStaffProfile> opTeacher = teachingStaffProfileRepository.findById(id);
        if(opTeacher.isEmpty()) return;

        TeachingStaffProfile teacher = opTeacher.get();
        teacher.setFirstName(request.getFirstName());
        teacher.setLastName(request.getLastName());
        teacher.setBiography(request.getBiography());
        teacher.setProfilePictureUrl(request.getProfilePictureUrl());
        teacher.setDepartment(request.getDepartment());
        teachingStaffProfileRepository.save(teacher);

        officeHourRepository.deleteAllByTeacher(teacher.getTeacherId());
        teachingStaffContactRepository.deleteAllById_Teacher(teacher.getTeacherId());

        request.getOfficeHours().forEach(officeHourModel ->
                officeHourRepository.save(officeHourModel.officeHourfromModel(teacher)));

        request.getContacts().forEach(contactModel ->
                teachingStaffContactRepository.save(contactModel.contactfromModel(teacher)));
    }

    public void updateStudentData(int id,StudentProfileRequest request) {
        Optional<StudentProfile> opStudent = studentProfileRepository.findById(id);
        if(opStudent.isEmpty()) return;

        StudentProfile student = opStudent.get();
        student.setFirstName(request.getFirstName());
        student.setLastName(request.getLastName());
        student.setBiography(request.getBiography());
        student.setProfilePictureUrl(request.getProfilePictureUrl());
        student.setMajor(request.getMajor());
        student.setMinor(request.getMinor());
        student.setLevel(request.getLevel());
        student.setNoOfHours(request.getNoOfHours());
        student.setGpa(request.getGpa());

        studentProfileRepository.save(student);

        studentContactRepository.deleteAllById_Student(student.getStudentId());

        request.getContacts().forEach(contactModel ->
                studentContactRepository.save(contactModel.contactfromModel(student)));
    }

    public TeachingStaffProfileReqAndRes getTeachingStaffProfileInfo(Integer id) {
        Optional<TeachingStaffProfile> teacher = teachingStaffProfileRepository.findById(id);
        if(teacher.isEmpty())return null;

        TeachingStaffProfileReqAndRes response = teacher.get().toResponse();

        //todo: tommorrow
        var contact = teacher.get().getContacts();
        System.out.println(contact.get(0).getData());

        return response;
    }

    public StudentProfile getStudentProfileInfo(Integer id) {
        Optional<StudentProfile> student = studentProfileRepository.findById(id);
        return student.orElse(null);
    }

    public List<OfficeHour> getOfficeHour(Integer id) {
        Optional<TeachingStaffProfile> teacher = teachingStaffProfileRepository.findById(id);
        return teacher.map(TeachingStaffProfile::getOfficeHours).orElse(null);
    }

    public Integer addWarning(Integer id , WarningRequest request) {
        Optional<StudentProfile> student = studentProfileRepository.findById(id);
        if(student.isEmpty())
            return null;
        Warning warning = Warning.builder()
                .reason(request.getReason())
                .date(request.getDate())
                .student(student.get())
                .build();
        return warningRepository.save(warning).getWarningId();
    }
}
