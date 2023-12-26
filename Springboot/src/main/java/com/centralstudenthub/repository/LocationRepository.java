package com.centralstudenthub.repository;

import com.centralstudenthub.entity.sessions.location.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import com.centralstudenthub.entity.sessions.location.LocationId;

public interface LocationRepository extends JpaRepository<Location, LocationId> {
}
