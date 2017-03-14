package com.spring.start.controller;

import lombok.experimental.var;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Mateusz on 11.03.2017.
 */
@Controller
@Log4j
@var
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

        var errors = getErrorAttributes(context, true);

        var stacktraceKey = "trace";

        var hasStacktrace = errors.containsKey(stacktraceKey);
        model.addAttribute("hasStacktrace", hasStacktrace);

        if (hasStacktrace) {
            model.addAttribute("stacktrace", errors.get(stacktraceKey));
        }

        var errorDetails = errors
                .entrySet()
                .stream()
                .filter(e -> !e.getKey().equals(stacktraceKey))
                .sorted(Comparator.comparing(Map.Entry::getKey))
                .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()));

        model.addAttribute("errorDetails", errorDetails);

        log.info(String.format("hasStacktrace = %1$b, details: %2$s", hasStacktrace, errors.toString()));

        return PAGES + SLASH + ERROR;
    }

    @Override
    public String getErrorPath() {
        return SLASH + ERROR + SLASH;
    }

    private Map<String, Object> getErrorAttributes(HttpServletRequest request, boolean includeStackTrace) {
        var requestAttributes = new ServletRequestAttributes(request);
        return errorAttributes.getErrorAttributes(requestAttributes, includeStackTrace);
    }
}
