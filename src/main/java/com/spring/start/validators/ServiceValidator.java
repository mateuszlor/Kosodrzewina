package com.spring.start.validators;

import com.spring.start.entity.DictionaryType;
import com.spring.start.service.dto.ServiceDto;
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
 * Created by Vertig0 on 01.04.2017.
 */
@Component
@Log4j
public class ServiceValidator implements Validator {

    // dd/mm/yyyy, dd-mm-yyyy, dd.mm.yyyy
    private static final String DATE_PATTERN = "^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[1,3-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$";


    @Autowired
    @Getter
    @Setter
    private Environment environment;


    @Override
    public boolean supports(Class<?> aClass) {
        return ServiceDto.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ServiceDto service = (ServiceDto) o;

        ValidationUtils.rejectIfEmpty(errors, "car", environment.getProperty("error.emptyField"));
        ValidationUtils.rejectIfEmpty(errors, "type", environment.getProperty("error.emptyField"));
        ValidationUtils.rejectIfEmpty(errors, "name", environment.getProperty("error.emptyField"));
        ValidationUtils.rejectIfEmpty(errors, "date", environment.getProperty("error.emptyField"));

        if (DictionaryType.PAYMENT.toString().equals(service.getType())) {
            ValidationUtils.rejectIfEmpty(errors, "dateTo", environment.getProperty("error.emptyField"));
        }

        if (!service.getDate().matches(DATE_PATTERN)){
            errors.rejectValue("date", environment.getProperty("error.invalidText"));
            log.warn("Nieprawidłowa data");
        }

        if (!service.getDateTo().matches(DATE_PATTERN) && DictionaryType.PAYMENT.toString().equals(service.getType())){
            errors.rejectValue("dateTo", environment.getProperty("error.invalidText"));
            log.warn("Nieprawidłowa data końca");
        }

    }
}
