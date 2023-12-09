package com.centralstudenthub.entity;

import jakarta.persistence.*;

import java.sql.Time;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(name = "office_hour")
public class OfficeHour {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer officeHourId;

    private Time fromTime;
    private Time toTime;
    private String weekDay;
    private String location;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacherId", nullable = false)
    private TeachingStaffProfile teacher;

}