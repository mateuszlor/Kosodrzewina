package com.spring.start.validators;

import com.spring.start.entity.Dictionary;
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
public class DictionaryValidator implements Validator {

    private static final String TEXT_PATTERN = "([a-zA-Z0-9\\s])*";

    @Autowired
    @Getter @Setter
    private Environment environment;

    @Override
    public boolean supports(Class<?> aClass) {
        return Dictionary.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        String dictionary = (String) o;

        ValidationUtils.rejectIfEmpty(errors, "name", environment.getProperty("error.emptyField"));

        if (!dictionary.matches(TEXT_PATTERN)){
            errors.rejectValue("brand", environment.getProperty("error.invalidText"));
            log.warn("Nieprawidłowa wartość pola nazwa");
        }

    }
}

