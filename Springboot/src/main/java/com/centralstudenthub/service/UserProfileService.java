package com.centralstudenthub.service;

import com.centralstudenthub.Model.Request.*;
import com.centralstudenthub.Model.Request.StudentProfileRequest;
import com.centralstudenthub.Model.Request.TeachingStaffProfileReqAndRes;
import com.centralstudenthub.Model.Request.WarningRequest;
import com.centralstudenthub.Model.Response.StudentProfileResponse;
import com.centralstudenthub.entity.student_profile.StudentProfile;
import com.centralstudenthub.entity.student_profile.Warning;
import com.centralstudenthub.entity.student_profile.course.student_course_grades.StudentCourseGrade;
import com.centralstudenthub.entity.student_profile.student_contacts.StudentContact;
import com.centralstudenthub.entity.teacher_profile.OfficeHour;
import com.centralstudenthub.entity.teacher_profile.TeachingStaffProfile;
import com.centralstudenthub.entity.teacher_profile.teaching_staff_contacts.TeachingStaffContact;
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

    @Autowired
    private StudentCourseGradeRepository studentCourseGradeRepository;

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

        List<TeachingStaffContact> contacts = teachingStaffContactRepository.getAllById_Teacher(teacher.get().getTeacherId());
        List<ContactModel> contactModels = contacts.stream().map(contact -> contact.modelFromTeachingStaffContact()).toList();

        List<OfficeHour> officeHours = officeHourRepository.getAllById_Teacher(teacher.get().getTeacherId());
        List<OfficeHourModel> officeHourModels= officeHours.stream().map(officeHour -> officeHour.modelFromOfficeHour()).toList();

        TeachingStaffProfileReqAndRes response = teacher.get().toResponse();
        response.setContacts(contactModels);
        response.setOfficeHours(officeHourModels);

        return response;
    }

    public StudentProfileRequest getStudentProfileInfo(Integer id) {
        Optional<StudentProfile> studentProfile = studentProfileRepository.findById(id);
        if (studentProfile.isEmpty()) return null;

        StudentProfileRequest response = studentProfile.get().modelFromStudentProfile();
        response.setGrades(studentCourseGradeRepository.findAllStudentCoursesGradesByStudentId(1).stream().map(
                StudentCourseGrade::modelFromGrade).toList());
        response.setContacts(studentContactRepository.findAllByStudentId(id).stream().map(
                StudentContact::modelFromStudentContact).toList());
        response.setWarnings(warningRepository.findAllByStudentId(id).stream().map(
                Warning::modelFromWarning).toList());

        return response;
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
