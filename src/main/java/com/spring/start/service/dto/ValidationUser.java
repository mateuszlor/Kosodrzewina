package com.spring.start.service.dto;

import com.spring.start.validators.EmailConstraint;
import com.spring.start.validators.FieldsValuesMatch;
import com.spring.start.validators.TextConstraint;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Nex0Zero on 2017-03-20.
 */
@FieldsValuesMatch.List({
    @FieldsValuesMatch(
        field = "password",
        fieldMatch = "repeatPassword",
        message = "Hasła są różne"
    )
})
@Getter @Setter
public class ValidationUser {

    private String username;

    @TextConstraint
    private String name;

    @TextConstraint
    private String surname;

    @EmailConstraint
    private String email;

    private String password;

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
