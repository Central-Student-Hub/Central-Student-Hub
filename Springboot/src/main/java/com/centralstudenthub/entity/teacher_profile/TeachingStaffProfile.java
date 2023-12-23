package com.centralstudenthub.entity.teacher_profile;

import com.centralstudenthub.Model.Request.TeachingStaffProfileReqAndRes;
import com.centralstudenthub.entity.student_profile.course.semester_courses.sessions.Session;
import com.centralstudenthub.entity.teacher_profile.teaching_staff_contacts.TeachingStaffContact;
import jakarta.persistence.*;

import java.util.List;

import lombok.*;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "teaching_staff_profile")
public class TeachingStaffProfile {

    @Id
    @Column(nullable = false, updatable = false)
    private Integer teacherId;

    private String firstName;
    private String lastName;
    private String biography;
    private String profilePictureUrl;
    private String department;

    @OneToMany(mappedBy = "teacher")
    private List<OfficeHour> officeHours;

    @OneToMany(mappedBy = "id.teacher")
    private List<TeachingStaffContact> contacts;

    @OneToMany(mappedBy = "teacher")
    private List<Session> sessions;

    public TeachingStaffProfileReqAndRes toResponse(){
        return TeachingStaffProfileReqAndRes.builder()
                .biography(biography)
                .department(department)
                .firstName(firstName)
                .lastName(lastName)
                .profilePictureUrl(profilePictureUrl)
                .build();
    }

}
