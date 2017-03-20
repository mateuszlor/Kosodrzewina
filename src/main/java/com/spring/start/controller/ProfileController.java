package com.spring.start.controller;

import com.spring.start.helper.ControllerHelper;
import lombok.experimental.var;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @RequestMapping(value = SLASH + PROFILE, method = RequestMethod.GET)
    public String get(Model model) {
        log.info("User profile page");

        ControllerHelper.setUserData(model);

        return PAGES + SLASH + PROFILE;
    }

    public String postData(){


        return PAGES + SLASH + PROFILE;
    }
}