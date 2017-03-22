package com.spring.start.validators;

import com.spring.start.service.dto.CustomerDto;
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
 * Created by Vertig0 on 18.03.2017.
 */
@Component
@Log4j
public class CustomerValidator implements Validator{

    private static final String TEXT_PATTERN = "([a-zA-Z]|[ŁłŻż]){1}([a-z]|[ąęóźćżśł])*";
    private static final String PHONE_PATTERN = "([0-9]{6,11})";

    @Autowired
    @Getter @Setter
    private Environment environment;

    @Override
    public boolean supports(Class<?> aClass) {
        return CustomerDto.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        CustomerDto customer = (CustomerDto) o;

        ValidationUtils.rejectIfEmpty(errors, "name", environment.getProperty("error.emptyField"));
        ValidationUtils.rejectIfEmpty(errors, "surname", environment.getProperty("error.emptyField"));

        if (!customer.getName().matches(TEXT_PATTERN)){
            errors.rejectValue("name", environment.getProperty("error.invalidText"));
            log.warn("Nieprawidłowe imię klienta");
        }

        if (!customer.getSurname().matches(TEXT_PATTERN)){
            errors.rejectValue("surname", environment.getProperty("error.invalidText"));
            log.warn("Nieprawidłowe nazwisko klienta");
        }

        if (!customer.getPhone().matches(PHONE_PATTERN)){
            errors.rejectValue("phone", environment.getProperty("error.invalidPhoneNumber"));
            log.warn("Nieprawidłowy numer telefonu");
        }

    }
}
