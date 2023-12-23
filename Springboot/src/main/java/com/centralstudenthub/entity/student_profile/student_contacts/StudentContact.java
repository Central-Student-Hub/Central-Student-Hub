package com.centralstudenthub.entity.student_profile.student_contacts;

import com.centralstudenthub.Model.Request.ContactModel;
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

    public ContactModel modelFromStudentContact() {
        return ContactModel.builder()
                .label(id.getLabel())
                .data(data)
                .build();
    }
}
