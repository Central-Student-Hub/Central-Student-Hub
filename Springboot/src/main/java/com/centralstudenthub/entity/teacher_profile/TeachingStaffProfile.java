package com.centralstudenthub.entity.teacher_profile;

import com.centralstudenthub.Model.Response.teacher_profile.TeachingStaffProfileModel;
import com.centralstudenthub.entity.sessions.Session;
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

    public TeachingStaffProfileModel toModel(){
        return TeachingStaffProfileModel.builder()
                .id(teacherId)
                .firstName(firstName)
                .lastName(lastName)
                .biography(biography)
                .profilePictureUrl(profilePictureUrl)
                .department(department)
                .build();
    }

}
