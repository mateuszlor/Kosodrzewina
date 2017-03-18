package com.spring.start.controller;

import com.spring.start.service.CustomerService;
import com.spring.start.service.dto.CustomerDto;
import com.spring.start.validator.CustomerValidator;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Created by Vertig0 on 17.03.2017.
 */
@Controller
@Log4j
public class CustomerController {

    private static final String SLASH = "/";
    private static final String PAGES = "pages";
    private static final String CUSTOMER = "new-customer";

//    @Autowired
//    @Getter @Setter
//    private CustomerService customerService;
//
//    @Autowired
//    @Getter @Setter
//    private CustomerValidator validator;

    @Autowired
    @Getter @Setter
    private Environment environment;

    @RequestMapping(name = SLASH + CUSTOMER, method = RequestMethod.GET)
    public String showNewCustomerPage() throws Exception {

        log.info("Add new customer page");
        return PAGES + SLASH + CUSTOMER;
    }

//    @RequestMapping(path = SLASH + CUSTOMER, method = RequestMethod.POST)
//    public String registerAccount(@Valid @ModelAttribute("newCustomer") CustomerDto customerDto,
//                                  BindingResult bindingResult, Model model) {
//
//        validator.validate(customerDto, bindingResult);
//        if(bindingResult.hasErrors()){
//            model.addAttribute("error", environment.getProperty("error.form.invalidValues"));
//            log.info("Wprowadzono niepoprawne wartosci do formularza dodawania nowego klienta");
//        }
//        try {
//            customerService.createCustomer(customerDto);
//            model.addAttribute("info", "Pomyślnie utworzono nowego klienta");
//            log.info("Pomyślnie zarejestrowano nowego użytkownika: " + customerDto.getUsername());
//        } catch (Exception e){
//            log.error("Nie udało się dodać użytkownika " + customerDto.getName() + "do bazy: " + e);
//        }
//
//        return "redirect:" + SLASH + CUSTOMER;
//    }


}
