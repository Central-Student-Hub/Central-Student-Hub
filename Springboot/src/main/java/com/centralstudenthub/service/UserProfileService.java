package com.centralstudenthub.service;

import com.centralstudenthub.Model.Request.StudentProfileRequest;
import com.centralstudenthub.Model.Request.TeachingStaffProfileRequest;
import com.centralstudenthub.Model.Request.WarningRequest;
import com.centralstudenthub.entity.student_profile.StudentProfile;
import com.centralstudenthub.entity.student_profile.Warning;
import com.centralstudenthub.entity.teacher_profile.OfficeHour;
import com.centralstudenthub.entity.teacher_profile.TeachingStaffProfile;
import com.centralstudenthub.repository.StudentProfileRepository;
import com.centralstudenthub.repository.TeachingStaffProfileRepository;
import com.centralstudenthub.repository.WarningRepository;
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
    private WarningRepository warningRepository;

    public void updateTeachingStaffData(TeachingStaffProfileRequest request) {
        TeachingStaffProfile teacher = TeachingStaffProfile.builder()
                .teacherId(request.getTeacherId())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .biography(request.getBiography())
                .profilePictureUrl(request.getProfilePictureUrl())
                .department(request.getDepartment())
                .officeHours(request.getOfficeHours())
                .contacts(request.getContacts()).build();
        teachingStaffProfileRepository.save(teacher);
    }

    public void updateStudentData(StudentProfileRequest request) {
        StudentProfile student = StudentProfile.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .biography(request.getBiography())
                .profilePictureUrl(request.getProfilePictureUrl())
                .major(request.getMajor())
                .minor(request.getMinor())
                .level(request.getLevel())
                .noOfHours(request.getNoOfHours())
                .gpa(request.getGpa())
                .contacts(request.getContacts())
                .warnings(request.getWarnings())
                .grades(request.getGrades())
                .assignmentAnswers(request.getAssignmentAnswers())
                .registrations(request.getRegistrations())
                .build();
        studentProfileRepository.save(student);
    }

    public TeachingStaffProfile getTeachingStaffProfileInfo(Integer id) {
        Optional<TeachingStaffProfile> teacher = teachingStaffProfileRepository.findById(id);
        return teacher.orElse(null);
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
