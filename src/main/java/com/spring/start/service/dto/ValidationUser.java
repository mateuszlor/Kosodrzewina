package com.spring.start.service.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Nex0Zero on 2017-03-20.
 */
public class ValidationUser {

    @Getter @Setter
    private String username;

    @Getter @Setter
    private String name;

    @Getter @Setter
    private String surname;

    @Getter @Setter
    private String email;

    @Getter @Setter
    private String password;

    @Getter @Setter
    private String repeatPassword;

    public ValidationUser(){}

    public ValidationUser(String username, String name, String surname, String password, String repeatPassword, String email) {
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.repeatPassword = repeatPassword;
        this.email = email;
    }

}
