package com.spring.start.service.dto;

import com.spring.start.validators.RegistrationNumberConstraint;
import lombok.*;

import javax.validation.constraints.NotNull;

/**
 * Created by Vertig0 on 22.03.2017.
 */

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarDto {

    private long id;

    @NotNull
    private String brand;

    @NotNull
    private String model;

    @RegistrationNumberConstraint
    private String registrationNumber;

    private String name;

    private Boolean isTrailer;
}
