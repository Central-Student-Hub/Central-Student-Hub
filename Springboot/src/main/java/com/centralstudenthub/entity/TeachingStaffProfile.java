package com.centralstudenthub.entity;

import jakarta.persistence.*;

import java.util.Set;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(name = "teaching_staff_profile")
public class TeachingStaffProfile {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer teacherId;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String biography;

    @Column
    private String profilePictureUrl;

    @Column
    private String department;

    @OneToMany(mappedBy = "teacher")
    private Set<OfficeHour> teacherOfficeHours;

    @OneToMany(mappedBy = "teacher")
    private Set<TeachingStaffContact> teacherTeachingStaffContacts;

}
