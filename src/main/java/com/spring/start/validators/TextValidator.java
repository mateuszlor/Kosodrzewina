package com.spring.start.validators;

import lombok.extern.log4j.Log4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Log4j
public class TextValidator implements ConstraintValidator<TextConstraint, String>{

    private static final String TEXT_PATTERN = "([A-z]|[ŁłŻż]){1}([a-z]|[ąęóźćżśł])*";

    @Override
    public void initialize(TextConstraint tetxConstraint) {
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        boolean isValid = s != null && s.matches(TEXT_PATTERN);
        if (!isValid){
            log.info("WALIDACJA: Wprowadzono nieprawidłowy tekst");
        }
        return isValid;
    }
}
