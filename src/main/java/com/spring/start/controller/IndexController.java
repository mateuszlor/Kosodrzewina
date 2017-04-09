package com.spring.start.controller;

import com.spring.start.service.ServiceService;
import lombok.experimental.var;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Vertig0 on 06.03.2017.
 */
@Controller
@Log4j
@var
public class IndexController {

    @Autowired
    private ServiceService serviceService;


    @Value("${jobs.serviceReminderTask.daysToCheck}")
    private String daysString;

    private static final String SLASH = "/";
    private static final String PAGES = "pages";
    private static final String DASHBOARD = "dashboard";

    @RequestMapping(value = {SLASH + DASHBOARD}, method = RequestMethod.GET)
    public String get(Model model) {
        log.info("Main page");

        log.info("About to check expiring services");

        log.info(String.format("Days string from properties: %s", daysString));
        var daysToCheck = 14;//Integer.parseInt(daysString);

        log.info(String.format("daysToCheck = %s", daysToCheck));
        var expiringServicesCount = serviceService.getServicesSoonToExpireCount(daysToCheck);

        log.info(String.format("Got %s services", expiringServicesCount));

        model.addAttribute("expiringServicesCount", expiringServicesCount);

        return PAGES + SLASH + DASHBOARD;
    }
}