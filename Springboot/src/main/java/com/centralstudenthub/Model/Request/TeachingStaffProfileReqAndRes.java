package com.centralstudenthub.Model.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TeachingStaffProfileReqAndRes {
    private String firstName;
    private String lastName;
    private String biography;
    private String profilePictureUrl;
    private String department;
    private List<OfficeHourModel> officeHours;
    private List<ContactModel> contacts;
}

