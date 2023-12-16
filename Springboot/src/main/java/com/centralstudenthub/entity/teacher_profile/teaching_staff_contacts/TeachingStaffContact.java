package com.centralstudenthub.entity.teacher_profile.teaching_staff_contacts;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "teaching_staff_contact")
public class TeachingStaffContact {

    @EmbeddedId
    private TeachingStaffContactId id;

    private String data;
}
