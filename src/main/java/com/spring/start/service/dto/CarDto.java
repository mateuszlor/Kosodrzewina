package com.spring.start.service.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Vertig0 on 22.03.2017.
 */
public class CarDto {

    @Getter
    @Setter
    private long id;

    @Getter @Setter
    private String brand;

    @Getter @Setter
    private String model;

    @Getter @Setter
    private String registrationNumber;

    @Getter @Setter
    private String name;

    @Getter @Setter
    private Boolean isTrailer;


}
