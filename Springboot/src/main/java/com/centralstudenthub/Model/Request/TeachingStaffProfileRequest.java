package com.centralstudenthub.Model.Request;

import com.centralstudenthub.entity.teacher_profile.OfficeHour;
import com.centralstudenthub.entity.teacher_profile.teaching_staff_contacts.TeachingStaffContact;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeachingStaffProfileRequest {
    private Integer teacherId;
    private String firstName;
    private String lastName;
    private String biography;
    private String profilePictureUrl;
    private String department;
    private List<OfficeHour> officeHours;
    private List<TeachingStaffContact> contacts;
}
