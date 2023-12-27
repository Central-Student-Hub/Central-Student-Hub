package com.centralstudenthub.Model.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExamResponse {
    private String courseName;
    private Integer building;
    private Integer seatNumber;
    private Integer room;
    private Date date;
    private float fromTime;
    private float period;
}
