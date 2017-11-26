package com.spring.start.validators;

import lombok.extern.log4j.Log4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Log4j
public class RegistrationNumberValidator implements ConstraintValidator<RegistrationNumberConstraint, String>{

    private static final String REGISTRATION_NUMBER_PATTERN = "([A-Z0-9]){2,3}(\\s)?([A-Z0-9]){3,5}";

    @Override
    public void initialize(RegistrationNumberConstraint registrationNumberConstraint) {}

    @Override
    public boolean isValid(String contactField, ConstraintValidatorContext cxt) {
        boolean isValid = contactField != null && contactField.matches(REGISTRATION_NUMBER_PATTERN);
        if (!isValid){
            log.info("WALIDACJA: Wprowadzono niepoprawny numer rejestracyjny");
        }
        return isValid;
    }

}
