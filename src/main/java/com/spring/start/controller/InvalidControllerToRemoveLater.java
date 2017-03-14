package com.spring.start.controller;

import lombok.experimental.var;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Mateusz on 13.03.2017.
 */
@Controller
@Log4j
public class InvalidControllerToRemoveLater {

    private static final String SLASH = "/";
    private static final String PAGES = "pages";
    private static final String INVALID = "invalid";

    @RequestMapping(value = {SLASH + INVALID}, method = RequestMethod.GET)
    public String indexPage() throws Exception {
        log.info("Strona testowa - exception");
        if(true){
            var inner = new RuntimeException("Coś się zadziało ŹLE!!!");
            throw new Exception("BLA Bla bla...", inner);
        }
        // never happens
        return PAGES + SLASH + INVALID;
    }
}