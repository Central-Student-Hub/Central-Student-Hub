package com.centralstudenthub.Model.Request;

import com.centralstudenthub.entity.teacher_profile.OfficeHour;
import com.centralstudenthub.entity.teacher_profile.TeachingStaffProfile;
import lombok.Builder;

import java.sql.Time;

@Builder
public record OfficeHourModel(Integer officeHourId, Time fromTime, Time toTime, String weekDay, String location) {

    public OfficeHour officeHourfromModel(TeachingStaffProfile tsp){
        return  OfficeHour.builder()
                .fromTime(fromTime)
                .toTime(toTime)
                .weekDay(weekDay)
                .location(location)
                .teacher(tsp)
                .build();
    }
}
