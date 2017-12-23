package com.spring.start.controller;

import com.spring.start.entity.DictionaryType;
import com.spring.start.service.CarService;
import com.spring.start.service.DictionaryService;
import com.spring.start.service.ServiceService;
import com.spring.start.service.dto.ServiceDto;
import com.spring.start.service.dto.UserDto;
import com.spring.start.validators.ServiceValidator;
import lombok.experimental.var;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Collections;
import java.util.stream.Collectors;

/**
 * Created by Vertig0 on 29.03.2017.
 */
@Controller
@Log4j
@var
public class ServiceController extends BaseController{

    @Autowired
    private DictionaryService dictionaryService;

    @Autowired
    private CarService carService;

    @Autowired
    private ServiceService serviceService;

    @Autowired
    private ServiceValidator validator;

    private static final String SLASH = "/";
    private static final String PAGES = "pages";
    private static final String SERVICES = "services";
    private static final String ADD_SERVICE = "add-service";

    @RequestMapping(value = SLASH + ADD_SERVICE, method = RequestMethod.GET)
    public String showNewServicePage(Model model) throws Exception {

        model.addAttribute("cars", carService.findAllActive());
        model.addAttribute("serviceDict", dictionaryService.getDictionaryiesByType(DictionaryType.SERVICE));
        model.addAttribute("paymentDict", dictionaryService.getDictionaryiesByType(DictionaryType.PAYMENT));

        log.info("Strona dodawania nowego wpisu serwisowego");
        return PAGES + SLASH + ADD_SERVICE;
    }

    @RequestMapping(path = SLASH + ADD_SERVICE, method = RequestMethod.POST)
    public String addService(@Valid @ModelAttribute("service") ServiceDto serviceDto,
                             BindingResult bindingResult, Model model,
                             RedirectAttributes redirectAttributes,
                             HttpServletRequest request) {

        validator.validate(serviceDto, bindingResult);
        if (bindingResult.hasErrors()) {
            addMessage(redirectAttributes, MessageType.ERROR, "message.service.add.error.validator",
                    bindingResult.getFieldErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.toList()));
            log.info("Wprowadzono niepoprawne wartosci do formularza dodawania serwisu");
            return "redirect:" + SLASH + ADD_SERVICE;
        }

        try {
            if (DictionaryType.SERVICE.toString().equals(serviceDto.getType())) {
                serviceService.createService(serviceDto, (UserDto) request.getSession().getAttribute("user"));
                log.debug("Dodano serwis do bazy");
            } else if (DictionaryType.PAYMENT.toString().equals(serviceDto.getType())) {
                serviceService.createPeriodicService(serviceDto, (UserDto) request.getSession().getAttribute("user"));
                log.debug("Dodano serwis okresowy do bazy");
            } else {
                log.error("Wystąpił problem z dodaniem serwisu, pusty typ wpisu serwisowego");
                return "redirect:" + SLASH + ADD_SERVICE;
            }
            addMessage(redirectAttributes, MessageType.SUCCESS, "message.service.add.success");
            log.info("Pomyślnie dodano serwis");
        } catch (Exception e) {
            addMessage(redirectAttributes, MessageType.ERROR, "message.service.add.error");
            log.error("Nie udało się dodać serwisu: " + e);
        }

        return "redirect:" + SLASH + ADD_SERVICE;
    }

    @RequestMapping(path = SLASH + SERVICES, method = RequestMethod.GET)
    public String listServices(Model model, HttpServletRequest request) {

        log.info("Services page");
        log.info(String.format("Attributes: %s", String.join(", ", Collections.list(request.getParameterNames()))));

        var type = request.getParameter("type");
        if(type == null) {
            type = "none";
        }
        model.addAttribute("type", type);

        log.info(String.format("Attribute type = %s", type));

        switch (type) {
            case "expiring": {
                var expiringServices = serviceService.getServicesSoonToExpire(14);

                log.info(String.format("Found %s services", expiringServices.size()));
                log.info(String.format("Services:\n\r%s",
                        String.join("\n\r",
                                expiringServices.stream()
                                        .map(s -> String.format("\t\t%s | %s | %s | %s | %s",
                                                s.getId(),
                                                s.getCar(),
                                                s.getType(),
                                                s.getDate(),
                                                s.getDateTo()))
                                        .collect(Collectors.toList()))));

                model.addAttribute("services", expiringServices);
                break;
            }
            default: {
                log.info("No search type provided - using default value");
                var services = serviceService.getAllServices();
                model.addAttribute("services", services);
                break;
            }
        }

        return PAGES + SLASH + SERVICES;
    }
}
