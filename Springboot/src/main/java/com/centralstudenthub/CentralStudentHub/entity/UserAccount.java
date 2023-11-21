package com.centralstudenthub.CentralStudentHub.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;
    private UserType userType;
    private String ssn;
    @Email
    private String email;
    private String passwordHash;
    private String passwordSalt;
    private Date passwordDate;

}



