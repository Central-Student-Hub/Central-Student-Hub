package com.centralstudenthub.Model.Response.sessions;

import com.centralstudenthub.entity.sessions.location.Location;
import com.centralstudenthub.entity.sessions.location.LocationId;
import lombok.Builder;

@Builder
public record LocationResponse (Integer building, Integer room, Integer capacity) {
    public Location toEntity() {
        return Location.builder()
                .id(
                    LocationId.builder()
                    .building(building)
                    .room(room)
                    .build()
                )
                .capacity(capacity)
                .build();
    }
}
