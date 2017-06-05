package com.spring.start.controller;

import com.itextpdf.text.DocumentException;
import com.spring.start.service.ReportService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Date;

/**
 * Created by Vertig0 on 04.06.2017.
 */
@Controller
@Log4j
public class ReportController {

    private static final String SLASH = "/";
    private static final String PAGES = "pages";
    private static final String REPORTS = "reports";
    private static final String GENERATE_REPORT = "generate";

    @Autowired
    private ReportService reportService;


    @RequestMapping(path = SLASH + REPORTS, method = RequestMethod.GET)
    public String showAddRentPage(Model model) {

        return PAGES + SLASH + REPORTS;
    }

    @RequestMapping(path = SLASH + REPORTS + SLASH + GENERATE_REPORT, method = RequestMethod.POST)
    public String addRent(@Valid @RequestParam("startDate") Date from,
                          @Valid @RequestParam("endDate") Date to,
                          @Valid @RequestParam("type") String type,
                          Model model) throws IOException, DocumentException {

//        reportService.createReport(from, to, type);
        return "redirect:" + SLASH + REPORTS;
    }

}
