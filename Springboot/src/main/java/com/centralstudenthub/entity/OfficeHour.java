package com.centralstudenthub.entity;

import jakarta.persistence.*;

import java.time.LocalTime;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(name = "office_hour")
public class OfficeHour {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer officeHourId;

    @Column
    private LocalTime fromTime;

    @Column
    private LocalTime toTime;

    @Column(length = 20)
    private String weekDay;

    @Column
    private String location;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacherId", nullable = false)
    private TeachingStaffProfile teacher;

}
