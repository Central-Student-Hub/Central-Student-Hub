package com.centralstudenthub.entity.sessions.location;

import com.centralstudenthub.Model.Response.sessions.LocationResponse;
import com.centralstudenthub.entity.sessions.Session;
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

    public LocationResponse toResponse() {
        return LocationResponse.builder()
                .building(id.getBuilding())
                .room(id.getRoom())
                .capacity(capacity)
                .build();
    }
}
