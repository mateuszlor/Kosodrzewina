package com.spring.start.controller;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Mateusz on 11.03.2017.
 */
@Controller
@Log4j
public class CustomErrorController implements ErrorController {

    private static final String SLASH = "/";
    private static final String PAGES = "pages";
    private static final String INDEX = "index";
    private static final String ERROR = "error";

    @Autowired
    private ErrorAttributes errorAttributes;

    @Autowired
    private HttpServletRequest context;

    @RequestMapping(value = SLASH + ERROR, method = RequestMethod.GET)
    public String errorPage(Model model) {
        log.info("Strona błędu");

        Map<String, Object> errors = getErrorAttributes(context, true);

        boolean hasStacktrace = errors.containsKey("stacktrace");
        model.addAttribute("hasStacktrace", hasStacktrace);

        if (hasStacktrace) {
            model.addAttribute("stacktrace", errors.get("stacktrace"));
        }

        List<String> errorDetails = errors
                .keySet()
                .stream()
                .map(k -> String.format("%1$s - %2$s", k, errors.get(k)))
                .sorted()
                .collect(Collectors.toList());

        model.addAttribute("errorDetails", errorDetails);

        log.info(String.format("hasStacktrace = %1$b, details: %2$s", hasStacktrace, errors.toString()));

        return PAGES + SLASH + ERROR;
    }

    @Override
    public String getErrorPath() {
        return SLASH + ERROR + SLASH;
    }

    private Map<String, Object> getErrorAttributes(HttpServletRequest request, boolean includeStackTrace) {
        RequestAttributes requestAttributes = new ServletRequestAttributes(request);
        return errorAttributes.getErrorAttributes(requestAttributes, includeStackTrace);
    }
}
