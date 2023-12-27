package com.centralstudenthub.service;

import java.util.List;
import java.util.Optional;

import com.centralstudenthub.Model.Request.StudentProfileRequest;
import com.centralstudenthub.Model.Request.WarningRequest;
import com.centralstudenthub.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.centralstudenthub.Model.Response.ContactResponse;
import com.centralstudenthub.Model.Response.teacher_profile.OfficeHourResponse;
import com.centralstudenthub.Model.Response.teacher_profile.TeachingStaffProfileModel;
import com.centralstudenthub.entity.student_profile.StudentProfile;
import com.centralstudenthub.entity.student_profile.Warning;
import com.centralstudenthub.entity.student_profile.student_contacts.StudentContact;
import com.centralstudenthub.entity.teacher_profile.OfficeHour;
import com.centralstudenthub.entity.teacher_profile.TeachingStaffProfile;
import com.centralstudenthub.entity.teacher_profile.teaching_staff_contacts.TeachingStaffContact;

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

    @Autowired
    private StudentCourseGradeRepository studentCourseGradeRepository;

    public UserProfileService() {
    }

    public void updateTeachingStaffData(int id, TeachingStaffProfileModel request) {

        Optional<TeachingStaffProfile> opTeacher = teachingStaffProfileRepository.findById(id);
        if (opTeacher.isEmpty()) return;

        TeachingStaffProfile teacher = opTeacher.get();
        teacher.setFirstName(request.getFirstName());
        teacher.setLastName(request.getLastName());
        teacher.setBiography(request.getBiography());
        teacher.setProfilePictureUrl(request.getProfilePictureUrl());
        teacher.setDepartment(request.getDepartment());
        teachingStaffProfileRepository.save(teacher);

        officeHourRepository.deleteAllByTeacher(teacher.getTeacherId());
        teachingStaffContactRepository.deleteAllById_Teacher(teacher.getTeacherId());

        if (request.getOfficeHours() != null) {
            request.getOfficeHours().forEach(officeHourResponse ->
                    officeHourRepository.save(officeHourResponse.toEntity(teacher)));
        }

        if (request.getContacts() != null) {
            request.getContacts().forEach(contactModel ->
                    teachingStaffContactRepository.save(contactModel.toEntity(teacher)));
        }
    }

    public boolean updateStudentData(int id, StudentProfileRequest request) {
        Optional<StudentProfile> opStudent = studentProfileRepository.findById(id);
        if(opStudent.isEmpty()) return false;

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

        if (request.getContacts() != null) {
            request.getContacts().forEach(contactModel ->
                    studentContactRepository.save(contactModel.toEntity(student)));
        }

        return true;
    }

    public TeachingStaffProfileModel getTeachingStaffProfileInfo(Integer id) {
        Optional<TeachingStaffProfile> teacher = teachingStaffProfileRepository.findById(id);
        if (teacher.isEmpty()) return null;

        List<TeachingStaffContact> contacts = teachingStaffContactRepository.getAllById_Teacher(teacher.get().getTeacherId());
        List<ContactResponse> contactResponses = contacts.stream().map(TeachingStaffContact::toResponse).toList();

        List<OfficeHour> officeHours = officeHourRepository.getAllById_Teacher(teacher.get().getTeacherId());
        List<OfficeHourResponse> officeHourModels= officeHours.stream().map(OfficeHour::toResponse).toList();

        TeachingStaffProfileModel response = teacher.get().toResponse();
        response.setContacts(contactResponses);
        response.setOfficeHours(officeHourModels);

        return response;
    }

    public StudentProfileRequest getStudentProfileInfo(Integer id) {
        Optional<StudentProfile> studentProfile = studentProfileRepository.findById(id);
        if (studentProfile.isEmpty()) return null;

        StudentProfileRequest response = studentProfile.get().modelFromStudentProfile();
        response.setContacts(studentContactRepository.findAllByStudentId(id).stream().map(
                StudentContact::toResponse).toList());

        return response;
    }

    public List<OfficeHour> getOfficeHour(Integer id) {
        Optional<TeachingStaffProfile> teacher = teachingStaffProfileRepository.findById(id);
        return teacher.map(TeachingStaffProfile::getOfficeHours).orElse(null);
    }

    public boolean addWarning(WarningRequest request) {
        Optional<StudentProfile> student = studentProfileRepository.findById(request.getStudentId());
        if (student.isEmpty())
            return false;
        Warning warning = Warning.builder()
                .reason(request.getReason())
                .date(request.getDate())
                .student(student.get())
                .build();
        warningRepository.save(warning);
        return true;
    }

    public List<TeachingStaffProfileModel> getAllTeachingStaff() {
        return teachingStaffProfileRepository.findAll().stream().map(TeachingStaffProfile::toResponse).toList();
    }

    public List<StudentProfileRequest> getAllStudents() {
        return studentProfileRepository.findAll().stream().map(StudentProfile::modelFromStudentProfile).toList();
    }
}
