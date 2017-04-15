package com.spring.start.validators;

import com.spring.start.entity.Rent;
import com.spring.start.service.dto.RentDto;
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
 * Created by Vertig0 on 14.04.2017.
 */
@Log4j
@Component
public class RentValidator implements Validator {

    private static final String DATE_PATTERN = "";

    @Autowired
    @Getter
    @Setter
    private Environment env;

    @Override
    public boolean supports(Class<?> aClass) {
        return RentDto.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        RentDto rentDto = (RentDto) o;

        ValidationUtils.rejectIfEmpty(errors, "customer", "error.emptyField");
        ValidationUtils.rejectIfEmpty(errors, "car", "error.emptyField");
        ValidationUtils.rejectIfEmpty(errors, "startDate", "error.emptyField");
//        ValidationUtils.rejectIfEmpty(errors, "endDate", "error.emptyField");
        ValidationUtils.rejectIfEmpty(errors, "income", "error.emptyField");

//        if (!rentDto.getStartDate().matches(DATE_PATTERN)){
//            errors.rejectValue("startDate","error.invalidText");
//            log.warn("Nieprawidłowe data rozpoczęcia");
//        }
//
//        if (!rentDto.getEndDate().matches(DATE_PATTERN)){
//            errors.rejectValue("endDate","error.invalidText");
//            log.warn("Nieprawidłowe data zakończenia");
//        }

        //TODO: data zakończenia nie wcześniejsza niż rozpoczęcia

    }

}
