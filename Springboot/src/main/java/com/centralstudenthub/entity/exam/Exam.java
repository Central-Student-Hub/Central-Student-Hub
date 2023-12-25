package com.centralstudenthub.entity.exam;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "exam")
public class Exam {
    @EmbeddedId
    private ExamId id;
    private int seatNumber;
    private int room;
    private int building;
    private Date date;
    private int period;
}
