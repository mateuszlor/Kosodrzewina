package com.spring.start.validators;

import lombok.extern.log4j.Log4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Log4j
public class PhoneNumberValidator implements ConstraintValidator<PhoneNumberConstraint, String>{

    private static final String PHONE_NUMBER_PATTERN = "([0-9]{6,11})";

    @Override
    public void initialize(PhoneNumberConstraint phoneNumberConstraint) {}

    @Override
    public boolean isValid(String contactField, ConstraintValidatorContext cxt) {
        boolean isValid = contactField != null && contactField.matches(PHONE_NUMBER_PATTERN);
        if (!isValid){
            log.info("WALIDACJA: Wprowadzono niepoprawny numer telefonu");
        }
        return isValid;
    }

}
