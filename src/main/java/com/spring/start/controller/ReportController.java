package com.spring.start.controller;

import com.itextpdf.text.DocumentException;
import com.spring.start.entity.ReportType;
import com.spring.start.operations.Functions;
import com.spring.start.service.ReportService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Vertig0 on 04.06.2017.
 */
@Controller
@Log4j
public class ReportController {

    private static final String APPLICATION_PDF = "application/pdf";
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
    public String downloadReport(@Valid @RequestParam("startDate") String from,
                          @Valid @RequestParam("endDate") String to,
                          @Valid @RequestParam("type") ReportType type,
                          @Valid @RequestParam("filename") String filename,
                          Model model) throws IOException, DocumentException {

        String newFileName = Functions.generateFilename(filename, from, to, type.getLabel());
        reportService.createReport(convertStringToDate(from), convertStringToDate(to), type, newFileName);
        return "redirect:/download?filename=" + newFileName;
    }

    @RequestMapping(value = "/download", method = RequestMethod.GET, produces = APPLICATION_PDF)
    public @ResponseBody
    void download(@RequestParam("filename") String filename, HttpServletResponse response) throws IOException {
        File file = getFile(filename);
        InputStream in = new FileInputStream(file);
        response.setContentType(APPLICATION_PDF);
        response.setHeader("Content-Disposition", "attachment; filename=" + file.getName());
        response.setHeader("Content-Length", String.valueOf(file.length()));
        FileCopyUtils.copy(in, response.getOutputStream());
        deleteFile(filename);
    }

    private Date convertStringToDate(String stringDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.ENGLISH);
        LocalDate date = LocalDate.parse(stringDate, formatter);
        return java.sql.Date.valueOf(date);
    }

    private File getFile(String filename) throws FileNotFoundException {
        File file = new File(filename);
        if (!file.exists()){
            throw new FileNotFoundException("file with path: " + filename + " was not found.");
        }
        return file;
    }

    private void deleteFile(String filename) {
        try{
            File file = new File(filename);
            if(file.delete()){
                log.debug("Plik usunięty");
            }else{
                log.error("Plik nie został usunięty");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
