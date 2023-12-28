package com.centralstudenthub.entity.teacher_profile;

import java.util.List;

import com.centralstudenthub.Model.Response.teacher_profile.TeachingStaffProfileModel;
import com.centralstudenthub.entity.sessions.Session;
import com.centralstudenthub.entity.teacher_profile.teaching_staff_contacts.TeachingStaffContact;

import jakarta.persistence.*;
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

    @OneToMany(mappedBy = "teacher",fetch = FetchType.EAGER)
    private List<Session> sessions;

    public TeachingStaffProfileModel toResponse(){
        return TeachingStaffProfileModel.builder()
                .id(teacherId)
                .biography(biography)
                .department(department)
                .firstName(firstName)
                .lastName(lastName)
                .biography(biography)
                .profilePictureUrl(profilePictureUrl)
                .department(department)
                .build();
    }

}
