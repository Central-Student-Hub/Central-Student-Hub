package com.centralstudenthub.entity;

import jakarta.persistence.*;

import java.util.Set;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(name = "location")
public class Location {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer room;

    @Id
    @Column(nullable = false)
    private Integer building;

    @Column
    private Integer capacity;

    @OneToMany(mappedBy = "location")
    private Set<Session> roomSessions;

}
