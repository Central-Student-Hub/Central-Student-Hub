package com.centralstudenthub.entity.student_profile.course.semester_courses.sessions.location;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LocationId implements Serializable {

    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer room;

    @Column(nullable = false)
    private Integer building;
}
