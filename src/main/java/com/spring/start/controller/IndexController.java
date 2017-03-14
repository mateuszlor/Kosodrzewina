package com.spring.start.controller;

import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Vertig0 on 06.03.2017.
 */
@Controller
@Log4j
public class IndexController {

    private static final String SLASH = "/";
    private static final String PAGES = "pages";
    private static final String INDEX = "index";

    @RequestMapping(value = {SLASH + INDEX}, method = RequestMethod.GET)
    public String indexPage() {
        log.info("Strona główna");
        return PAGES + SLASH + INDEX;
    }
}