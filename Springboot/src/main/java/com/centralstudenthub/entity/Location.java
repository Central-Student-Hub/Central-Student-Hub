package com.centralstudenthub.entity;

import jakarta.persistence.*;

import java.util.Set;

import lombok.*;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "location")
public class Location {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer room;

    @Id
    @Column(nullable = false)
    private Integer building;

    private Integer capacity;

    @OneToMany(mappedBy = "location")
    private Set<Session> sessions;
}
