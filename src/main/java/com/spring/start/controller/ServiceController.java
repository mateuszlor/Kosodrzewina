package com.spring.start.controller;

import com.spring.start.entity.DictionaryType;
import com.spring.start.helper.ControllerHelper;
import com.spring.start.service.CarService;
import com.spring.start.service.DictionaryService;
import com.spring.start.service.ServiceService;
import com.spring.start.service.dto.ServiceDto;
import com.spring.start.service.dto.UserDto;
import com.spring.start.validators.ServiceValidator;
import lombok.Getter;
import lombok.Setter;
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

/**
 * Created by Vertig0 on 29.03.2017.
 */
@Controller
@Log4j
public class ServiceController {

    @Autowired
    @Getter
    @Setter
    private DictionaryService dictionaryService;

    @Autowired
    @Getter
    @Setter
    private CarService carService;

    @Autowired
    @Getter
    @Setter
    private ServiceService serviceService;

    @Autowired
    @Getter
    @Setter
    private ServiceValidator validator;

    private static final String SLASH = "/";
    private static final String PAGES = "pages";
    private static final String ADD_SERVICE = "add-service";

    @RequestMapping(value = SLASH + ADD_SERVICE, method = RequestMethod.GET)
    public String showNewServicePage(Model model) throws Exception {

        ControllerHelper.setUserData(model);

        model.addAttribute("cars", carService.findAll());
        model.addAttribute("dictionaries", dictionaryService.findAll());

        log.info("Strona dodawania nowego wpisu serwisowego");
        return PAGES + SLASH + ADD_SERVICE;
    }

    @RequestMapping(path = SLASH + ADD_SERVICE, method = RequestMethod.POST)
    public String addService(@Valid @ModelAttribute("service") ServiceDto serviceDto,
                                   BindingResult bindingResult, Model model,
                                   RedirectAttributes redirectAttributes,
                                   HttpServletRequest request) {

        validator.validate(serviceDto, bindingResult);
        if(bindingResult.hasErrors()){
            log.info("Wprowadzono niepoprawne wartosci do formularza dodawania serwisu");
            return "redirect:" + SLASH + ADD_SERVICE;
        }

        try {
            if (DictionaryType.SERVICE.toString().equals(serviceDto.getType())) {
                serviceService.createService(serviceDto, (UserDto) request.getSession().getAttribute("user"));
                log.debug("Dodano serwis do bazy");
            } else if(DictionaryType.PAYMENT.toString().equals(serviceDto.getType())) {
                serviceService.createPeriodicService(serviceDto, (UserDto) request.getSession().getAttribute("user"));
                log.debug("Dodano 'płatność' do bazy");
            } else {
                log.error("Wystąpił problem z dodaniem serwisu, pusty typ wpisu serwisowego");
                return "redirect:" + SLASH + ADD_SERVICE;
            }
            log.info("Pomyślnie dodano serwis");
        } catch (Exception e){
            log.error("Nie udało się dodać serwisu: " + e);
        }

        return "redirect:" + SLASH + ADD_SERVICE;
    }



}
