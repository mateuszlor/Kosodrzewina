package com.spring.start.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by Vertig0 on 15.04.2017.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum RentStatus {

    RESERVED("Zarezerwowane", 1),
    RENTED("Wyporzyczone", 2),
    ENDED("Zakończone", 3);

    private String displayName;
    private int level;

}
