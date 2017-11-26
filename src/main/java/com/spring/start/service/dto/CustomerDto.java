package com.spring.start.service.dto;

import com.spring.start.validators.PhoneNumberConstraint;
import com.spring.start.validators.TextConstraint;
import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * Created by Vertig0 on 18.03.2017.
 */
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerDto {

    private long id;

    @TextConstraint
    private String name;

    @TextConstraint
    private String surname;

    private String username;

    private String address;

    @PhoneNumberConstraint
    private String phone;

}
