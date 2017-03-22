package com.spring.start.controller;

import com.spring.start.helper.ControllerHelper;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Vertig0 on 21.03.2017.
 */
@Controller
@Log4j
public class CarController {


    private static final String SLASH = "/";
    private static final String PAGES = "pages";
    private static final String ADD_NEW_CAR= "new-car";

    @RequestMapping(value = SLASH + ADD_NEW_CAR, method = RequestMethod.GET)
    public String showNewCustomerPage(Model model) throws Exception {

        ControllerHelper.setUserData(model);

        log.info("Add new customer page");
        return PAGES + SLASH + ADD_NEW_CAR;
    }




}
