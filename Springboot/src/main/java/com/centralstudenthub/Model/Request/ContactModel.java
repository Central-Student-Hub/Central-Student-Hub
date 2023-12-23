package com.centralstudenthub.Model.Request;

import com.centralstudenthub.entity.student_profile.StudentProfile;
import com.centralstudenthub.entity.student_profile.student_contacts.StudentContact;
import com.centralstudenthub.entity.student_profile.student_contacts.StudentContactId;
import com.centralstudenthub.entity.teacher_profile.TeachingStaffProfile;
import com.centralstudenthub.entity.teacher_profile.teaching_staff_contacts.TeachingStaffContact;
import com.centralstudenthub.entity.teacher_profile.teaching_staff_contacts.TeachingStaffContactId;
import lombok.Builder;

@Builder
public record ContactModel(String label, String data) {

    public TeachingStaffContact contactfromModel(TeachingStaffProfile tsp){
        TeachingStaffContactId teachingStaffContactId = TeachingStaffContactId.builder()
                .label(label)
                .teacher(tsp)
                .build();

        return  TeachingStaffContact.builder()
                .id(teachingStaffContactId)
                .data(data)
                .build();
    }
    public StudentContact contactfromModel(StudentProfile sp){
        StudentContactId studentContactId = StudentContactId.builder()
                .label(label)
                .student(sp)
                .build();

        return  StudentContact.builder()
                .id(studentContactId)
                .data(data)
                .build();
    }
}
