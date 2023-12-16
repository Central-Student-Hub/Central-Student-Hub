package com.centralstudenthub.entity.student_profile.student_contacts;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "student_contact")
public class StudentContact {

    @EmbeddedId
    private StudentContactId id;

    private String data;
}
