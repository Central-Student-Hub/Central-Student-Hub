package com.centralstudenthub.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "course_member")
public class CourseMember {

    @EmbeddedId
    private CourseMemberId id;
}
