package com.centralstudenthub.CentralStudentHub.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Date;
import java.util.Collection;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="useraccount")
public class UserAccount implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userAccountId;
    private String ssn;
    private String userType;
    private String email;
    private String passwordHash;
    private String passwordSalt;
    private Date passwordDate;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return passwordHash;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}



