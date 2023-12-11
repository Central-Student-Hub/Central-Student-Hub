package com.centralstudenthub.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "student_contact")
public class StudentContact {

    @EmbeddedId
    private StudentContactId id;

    private String data;
}
