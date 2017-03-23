package com.spring.start.validator;

import com.spring.start.service.UserService;
import com.spring.start.service.dto.CustomerDto;
import com.spring.start.service.dto.UserDto;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.var;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by Mateusz on 23.03.2017.
 */
@Component
@Log4j
@var
public class PasswordValidator implements Validator{

    @Autowired
    @Getter @Setter
    private Environment environment;

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public boolean supports(Class<?> aClass) {
        return CustomerDto.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        var user = (UserDto) o;

        log.info(String.format("About to validate user ID = %s, password = %s, oldPassword = %s, newPassword = %s, newPassword2 = %s",
                user.getId(), user.getPassword(), user.getOldPassword(), user.getNewPassword(), user.getNewPassword2()));

        var fromDatabase = userService.getUserPassword(user.getId());

        if (!bCryptPasswordEncoder.matches(user.getOldPassword(), fromDatabase)){
            errors.rejectValue("oldPassword", environment.getProperty("error.invalidPassword"));

            var oldPassword = bCryptPasswordEncoder.encode(user.getOldPassword());
            log.warn(String.format("Nieprawidłowe obecne hasło (jest: '%s', podano: '%s'", fromDatabase, oldPassword));
        }

        if (!user.getNewPassword().equals(user.getNewPassword2())){
            errors.rejectValue("newPassword", environment.getProperty("error.differentPasswords"));
            log.warn("Podane nowe hasła są różne");
        }

        if(!errors.hasErrors()) {
            log.info("Walidacja haseł pomyślna");
        }
    }
}
