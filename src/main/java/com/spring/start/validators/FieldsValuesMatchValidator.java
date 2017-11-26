package com.spring.start.validators;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Log4j
public class FieldsValuesMatchValidator implements ConstraintValidator<FieldsValuesMatch, String> {

    private String field;
    private String fieldMatch;

    public void initialize(FieldsValuesMatch constraintAnnotation) {
        this.field = constraintAnnotation.field();
        this.fieldMatch = constraintAnnotation.fieldMatch();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        Object fieldValue = new BeanWrapperImpl(s)
                .getPropertyValue(field);
        Object fieldMatchValue = new BeanWrapperImpl(s)
                .getPropertyValue(fieldMatch);

        if (fieldValue != null && fieldMatchValue != null) {
            return fieldValue.equals(fieldMatchValue);
        } else {
            return fieldMatchValue == null;
        }
    }
}
