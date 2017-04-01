package com.spring.start.controller;

import com.spring.start.helper.ControllerHelper;
import com.spring.start.service.UserService;
import com.spring.start.service.dto.UserDto;
import com.spring.start.validators.PasswordValidator;
import lombok.experimental.var;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.stream.Collectors;

/**
 * Created by Mateusz on 20.03.2017.
 */
@Controller
@Log4j
@var
public class ProfileController {

    private static final String SLASH = "/";
    private static final String PAGES = "pages";
    private static final String PROFILE = "profile";

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordValidator validator;

    @Autowired
    private Environment environment;

    @RequestMapping(value = SLASH + PROFILE, method = RequestMethod.GET)
    public String get(Model model) {
        log.info("User profile page");

        return PAGES + SLASH + PROFILE;
    }

    @RequestMapping(path = SLASH + PROFILE, method = RequestMethod.POST, params = "action=saveData")
    public String postData(@ModelAttribute("user") UserDto user) {

        log.info("Got POST with user: " + (user != null ? user.getFullName() : "NULL"));

        userService.editUserData(user);

        log.info("Modified user ID = " + user.getId());

        return "redirect:" + SLASH + PROFILE;
    }

    @RequestMapping(path = SLASH + PROFILE, method = RequestMethod.POST, params = "action=savePassword")
    public String postPassword(@Valid @ModelAttribute("user") UserDto user, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        log.info("Got POST with user: " + (user != null ? user.getFullName() : "NULL"));

        validator.validate(user, bindingResult);

        var hasErrors = bindingResult.hasErrors();

        log.info("hasErrors = " + hasErrors);

        if(hasErrors){
            log.warn("Błędne pola: " + String.join(", ", bindingResult
                    .getFieldErrors()
                    .stream()
                    .map(e -> e.getField())
                    .collect(Collectors.toList())));

            redirectAttributes.addFlashAttribute("error", environment.getProperty("error.form.invalidValues"));

            return "redirect:" + SLASH + PROFILE;
        }
        else {
            userService.editUserPassword(user);
            redirectAttributes.addFlashAttribute("info", environment.getProperty("message.user.edit.success"));
            log.info("Modified user password ID = " + user.getId());
            return "redirect:" + SLASH + PROFILE;
        }
    }
}