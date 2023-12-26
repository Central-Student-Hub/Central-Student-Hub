package com.centralstudenthub.entity.teacher_profile.teaching_staff_contacts;

import com.centralstudenthub.Model.Response.ContactResponse;
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

    public ContactResponse toResponse(){
        return ContactResponse.builder()
                .label(id.getLabel())
                .data(data)
                .build();
    }
}
