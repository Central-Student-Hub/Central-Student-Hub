package com.centralstudenthub.entity.teacher_profile.teaching_staff_contacts;

import com.centralstudenthub.entity.teacher_profile.TeachingStaffProfile;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TeachingStaffContactId implements Serializable {

    @Column(nullable = false, updatable = false)
    private String label;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacherId", nullable = false)
    private TeachingStaffProfile teacher;
}
