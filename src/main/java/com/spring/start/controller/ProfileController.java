package com.spring.start.controller;

import com.spring.start.helper.ControllerHelper;
import com.spring.start.service.UserService;
import com.spring.start.service.dto.UserDto;
import lombok.experimental.var;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

    @RequestMapping(value = SLASH + PROFILE, method = RequestMethod.GET)
    public String get(Model model) {
        log.info("User profile page");

        ControllerHelper.setUserData(model);

        return PAGES + SLASH + PROFILE;
    }

    @RequestMapping(path = SLASH + PROFILE, method = RequestMethod.POST, params = "action=saveData")
    public String postData(@ModelAttribute("user") UserDto user, BindingResult bindingResult, Model model) {

        log.info("Got POST with user: " + user != null ? user.getFullName() : "NULL");

        userService.editUserData(user);

        log.info("Modified user ID = " + user.getId());



        return "redirect:" + SLASH + PROFILE;
    }
}