package com.spring.start.controller;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Vertig0 on 10.03.2017.
 */
@Controller
@Log4j
public class LoginController {

    @Autowired
    private ApplicationContext context;

    private static final String SLASH = "/";
    private static final String PAGES = "pages";
    private static final String LOGIN = "login";

    @RequestMapping(value = {SLASH + LOGIN, SLASH}, method = RequestMethod.GET)
    public String loginPage(){
        log.info("Login page");
        return PAGES + SLASH + LOGIN;
    }

}
