package com.centralstudenthub.entity.student_profile.course.semester_courses.sessions.location;

import com.centralstudenthub.Model.LocationModel;
import com.centralstudenthub.entity.student_profile.course.semester_courses.sessions.Session;
import jakarta.persistence.*;

import java.util.Set;

import lombok.*;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "location")
public class Location {

    @EmbeddedId
    private LocationId id;

    private Integer capacity;

    @OneToMany(mappedBy = "location")
    private Set<Session> sessions;

    public LocationModel toModel() {
        return LocationModel.builder()
                .building(id.getBuilding())
                .room(id.getRoom())
                .capacity(capacity)
                .build();
    }
}
