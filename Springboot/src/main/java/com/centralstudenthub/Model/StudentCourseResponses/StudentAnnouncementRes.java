package com.centralstudenthub.Model.StudentCourseResponses;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentAnnouncementRes {
    private Long announcementId;
    private String announcementName;
    private String description;
}
