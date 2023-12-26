package com.centralstudenthub.Model.Response.teacher_profile;

import com.centralstudenthub.entity.teacher_profile.OfficeHour;
import com.centralstudenthub.entity.teacher_profile.TeachingStaffProfile;
import lombok.Builder;

import java.sql.Time;

@Builder
public record OfficeHourResponse(Integer officeHourId, Time fromTime, Time toTime, String weekDay, String location) {
    public OfficeHour toEntity(TeachingStaffProfile tsp){
        return  OfficeHour.builder()
                .fromTime(fromTime)
                .toTime(toTime)
                .weekDay(weekDay)
                .location(location)
                .teacher(tsp)
                .build();
    }
}
