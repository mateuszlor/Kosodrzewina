package com.spring.start.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by Vertig0 on 15.04.2017.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
public enum RentStatus {

    RESERVED("Zarezerwowane", 1),
    RENTED("Wyporzyczone", 2),
    ENDED("Zakończone", 3);

    private String displayName;
    private int level;

}
