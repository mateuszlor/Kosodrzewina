package com.spring.start.controller;

import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Vertig0 on 08.03.2017.
 */
@Controller
@Log4j
public class LoginController {

    private static final String SLASH = "/";
    private static final String LOGIN = "login";

    @RequestMapping(value = SLASH + LOGIN, method = RequestMethod.GET)
    public String loginPage(Model model, String error, String logout){
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }



}
