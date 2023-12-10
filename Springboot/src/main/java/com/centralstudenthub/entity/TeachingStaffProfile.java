package com.centralstudenthub.entity;

import jakarta.persistence.*;

import java.util.List;

import lombok.*;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "teaching_staff_profile")
public class TeachingStaffProfile {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer teacherId;

    private String firstName;
    private String lastName;
    private String biography;
    private String profilePictureUrl;
    private String department;

    @OneToMany(mappedBy = "teacher")
    private List<OfficeHour> officeHours;

    @OneToMany(mappedBy = "teacher")
    private List<TeachingStaffContact> contacts;

}
