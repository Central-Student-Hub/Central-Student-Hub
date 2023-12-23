package com.centralstudenthub.entity.student_profile.student_contacts;

import com.centralstudenthub.entity.student_profile.StudentProfile;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentContactId implements Serializable {

    @Column(nullable = false, updatable = false)
    private String label;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "studentId", nullable = false)
    private StudentProfile student;
}
