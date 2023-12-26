package com.centralstudenthub.Model;

import com.centralstudenthub.entity.student_profile.course.semester_courses.sessions.location.Location;
import com.centralstudenthub.entity.student_profile.course.semester_courses.sessions.location.LocationId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class LocationModel {
    Integer building;
    Integer room;
    Integer capacity;

    Location toLocation() {
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
