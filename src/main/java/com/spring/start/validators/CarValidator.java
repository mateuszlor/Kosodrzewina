package com.spring.start.validators;

import com.spring.start.service.dto.CarDto;
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
 * Created by Vertig0 on 22.03.2017.
 */
@Component
@Log4j
public class CarValidator implements Validator {

    private static final String TEXT_PATTERN = "([a-zA-Z])([a-z])*(\\s)?([a-z])*";
    private static final String REGISTRATION_NUMBER_PATTERN= "([A-Z]){2,3}(\\s)?([A-Z0-9]){3,5}";

    @Autowired
    @Getter @Setter
    private Environment environment;

    @Override
    public boolean supports(Class<?> aClass) {
        return CarDto.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        CarDto car = (CarDto) o;

        ValidationUtils.rejectIfEmpty(errors, "brand", environment.getProperty("error.emptyField"));
//        ValidationUtils.rejectIfEmpty(errors, "model", environment.getProperty("error.emptyField"));
        ValidationUtils.rejectIfEmpty(errors, "registrationNumber", environment.getProperty("error.emptyField"));

        if (!car.getBrand().matches(TEXT_PATTERN)){
            errors.rejectValue("brand", environment.getProperty("error.invalidText"));
            log.warn("Nieprawidłowa marka samochodu");
        }

//        if (!car.getModel().matches(TEXT_PATTERN)){
//            errors.rejectValue("model", environment.getProperty("error.invalidText"));
//            log.warn("Nieprawidłowy model samochodu");
//        }

        if (!car.getRegistrationNumber().matches(REGISTRATION_NUMBER_PATTERN)){
            errors.rejectValue("registrationNumber", environment.getProperty("error.invalidRegistrationNumber"));
            log.warn("Nieprawidłowy numer rejestracyjny");
        }

    }
}

