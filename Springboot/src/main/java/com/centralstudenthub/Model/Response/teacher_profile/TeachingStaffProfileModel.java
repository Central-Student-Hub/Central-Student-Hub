package com.centralstudenthub.Model.Response.teacher_profile;

import com.centralstudenthub.Model.Response.ContactResponse;
import com.centralstudenthub.Model.Response.teacher_profile.OfficeHourResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeachingStaffProfileModel {
    private Integer id;
    private String firstName;
    private String lastName;
    private String biography;
    private String profilePictureUrl;
    private String department;
    private List<OfficeHourResponse> officeHours;
    private List<ContactResponse> contacts;
}
