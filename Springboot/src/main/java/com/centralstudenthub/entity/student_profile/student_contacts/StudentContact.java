package com.centralstudenthub.entity.student_profile.student_contacts;

import com.centralstudenthub.Model.Response.ContactResponse;
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

    public ContactResponse toResponse() {
        return ContactResponse.builder()
                .label(id.getLabel())
                .data(data)
                .build();
    }
}
