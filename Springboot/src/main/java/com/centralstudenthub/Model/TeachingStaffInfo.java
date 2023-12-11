package com.centralstudenthub.Model;

import com.centralstudenthub.entity.OfficeHour;
import com.centralstudenthub.entity.TeachingStaffContact;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TeachingStaffInfo {
    private Integer teacherId;
    private String firstName;
    private String lastName;
    private String biography;
    private String profilePictureUrl;
    private String department;
    private List<OfficeHour> teacherOfficeHours;
    private List<TeachingStaffContact> teacherTeachingStaffContacts;
}