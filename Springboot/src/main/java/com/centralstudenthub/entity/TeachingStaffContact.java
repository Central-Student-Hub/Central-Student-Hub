package com.centralstudenthub.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "teaching_staff_contact")
public class TeachingStaffContact {

    @EmbeddedId
    private TeachingStaffContactId id;

    private String data;
}
