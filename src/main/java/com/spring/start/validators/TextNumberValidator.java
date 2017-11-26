package com.spring.start.validators;

import lombok.extern.log4j.Log4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Log4j
public class TextNumberValidator implements ConstraintValidator<TextNumberConstraint, String>{

    private static final String TEXT_PATTERN = "([a-zA-Z0-9\\s])*";

    @Override
    public void initialize(TextNumberConstraint textNumberConstraint) {

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        boolean isValid = s != null && s.matches(TEXT_PATTERN);
        if (!isValid){
            log.info("WALIDACJA: Wprowadzono niepoprawną wartość");
        }
        return isValid;
    }
}
