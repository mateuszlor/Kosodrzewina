package com.spring.start.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Vertig0 on 21.03.2017.
 */
@Entity
@Table(name = "car")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    @Getter @Setter
    private long id;

    @Column(name = "brand", nullable = false)
    @Getter @Setter
    private String brand;

    @Column(name = "model")
    @Getter @Setter
    private String model;

    @Column(name = "registration_number", nullable = false)
    @Getter @Setter
    private String registrationNumber;

    @Column(name = "name")
    @Getter @Setter
    private String name;

    @OneToMany(mappedBy = "car")
    @Getter @Setter
    Set<PeriodicService> periodicService;

    @OneToMany(mappedBy = "car")
    @Getter @Setter
    Set<Service> service;

    @Column(name = "is_trailer")
    @Getter @Setter
    private Boolean isTrailer = Boolean.FALSE;

}
