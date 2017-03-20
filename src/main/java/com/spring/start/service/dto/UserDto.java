package com.spring.start.service.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.var;
import lombok.extern.log4j.Log4j;

/**
 * Created by Mateusz on 19.03.2017.
 */
@Log4j
@var
@Getter
@Setter
@Builder
public class UserDto {
    private long id;
    private String username;
    private String name;
    private String surname;

    public String getFullName(){
        return name + " " + surname;
    }
}
