package com.centralstudenthub.Model.Request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExamRequest {
    private Long semCourseId;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date date;
    private float fromTime;
    private float period;
}
