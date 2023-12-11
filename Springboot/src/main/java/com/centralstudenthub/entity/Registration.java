package com.centralstudenthub.entity;

import jakarta.persistence.*;
import java.util.Date;

import lombok.*;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "registration")
public class Registration {

    @EmbeddedId
    private RegistrationId id;

    private Date paymentDeadline;
    private Double paymentFees;
}
