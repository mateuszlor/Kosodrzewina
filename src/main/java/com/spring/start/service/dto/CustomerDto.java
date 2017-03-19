package com.spring.start.service.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * Created by Vertig0 on 18.03.2017.
 */
public class CustomerDto {

    @Getter @Setter
    private long id;

    @Getter @Setter
    private String name;

    @Getter @Setter
    private String surname;

    @Getter @Setter
    private String username;

    @Getter @Setter
    private String address;

    @Getter @Setter
    private String phone;

    public CustomerDto(){}

    public CustomerDto(long id, String name, String surname, String username, String address, String phone) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.address = address;
        this.phone = phone;
    }
}
