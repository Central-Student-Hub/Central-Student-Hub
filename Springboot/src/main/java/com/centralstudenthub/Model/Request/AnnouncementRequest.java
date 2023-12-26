package com.centralstudenthub.Model.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnnouncementRequest {
    private Long announcementId;
    private String announcementName;
    private String description;
    private Long semCourseId;
}
