package com.spring.start.service.dto;

import com.spring.start.validators.RegistrationNumberConstraint;
import lombok.*;

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

    private String brand;

    private String model;

    @RegistrationNumberConstraint
    private String registrationNumber;

    private String name;

    private Boolean isTrailer;
}
