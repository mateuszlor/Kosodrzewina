package com.spring.start.validators;

import com.spring.start.entity.User;
import com.spring.start.service.dto.ValidationUser;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by Nex0Zero on 2017-03-17.
 */
@Log4j
@Component
public class RegisterValidator implements Validator {

    private static final String TEXT_PATTERN = "([a-zA-Z]|[Łł]){1}([a-z]|[ąęóźćżśł])*";
    private static final String EMAIL_PATTERN = "[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})";
    private static final String DATE_PATTERN = "[0-9]{2}.[0-9]{2}.[0-9]{4}";
    private static final String CREDENTIAL_PATTERN = "[a-zA-Z0-9]+";

    @Autowired
    @Getter
    @Setter
    private Environment env;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        ValidationUser user = (ValidationUser) o;

        ValidationUtils.rejectIfEmpty(errors, "name", "error.emptyField");
        ValidationUtils.rejectIfEmpty(errors, "surname", "error.emptyField");
        ValidationUtils.rejectIfEmpty(errors, "username", "error.emptyField");
        ValidationUtils.rejectIfEmpty(errors, "password", "error.emptyField");
        ValidationUtils.rejectIfEmpty(errors, "repeatPassword", "error.emptyField");

        if (!user.getName().matches(TEXT_PATTERN)){
            errors.rejectValue("name","error.invalidText");
            log.warn("Nieprawidłowe imie");
        }

        if (!user.getSurname().matches(TEXT_PATTERN)){
            errors.rejectValue("surname","error.invalidText");
            log.warn("Nieprawidłowe nazwisko");
        }

        if (!user.getUsername().matches(CREDENTIAL_PATTERN)){
            errors.rejectValue("username","error.invalidText");
            log.warn("Nieprawidłowy nazwa użytkownika");
        }

        if (!user.getPassword().matches(CREDENTIAL_PATTERN)){
            errors.rejectValue("password","error.invalidText");
            log.warn("Nieprawidłowe hasło");
        }

        if (!user.getRepeatPassword().equals(user.getPassword())){
            errors.rejectValue("repeatPassword","error.differentPasswords");
            log.warn("Hasła są różne");
        }


    }

}
