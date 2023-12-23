package com.centralstudenthub.entity.teacher_profile.teaching_staff_contacts;

import com.centralstudenthub.Model.Request.ContactModel;
import jakarta.persistence.*;
import lombok.*;

import javax.swing.text.html.parser.ContentModel;


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

    public ContactModel modelFromTeachingStaffContact(){
        return ContactModel.builder()
                .label(id.getLabel())
                .data(data)
                .build();
    }
}
