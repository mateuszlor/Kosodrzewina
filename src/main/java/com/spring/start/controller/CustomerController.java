package com.spring.start.controller;

import com.spring.start.entity.Customer;
import com.spring.start.service.CustomerService;
import com.spring.start.service.dto.CustomerDto;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    private static final String CUSTOMERS = "customers";
    private static final String EDIT_CUSTOMER = "edit-customer";
    private static final String DELETE_CUSTOMER = "delete-customer";

    @Autowired
    @Getter @Setter
    private CustomerService customerService;

    @Autowired
    @Getter @Setter
    private Environment environment;

    @RequestMapping(value = SLASH + CUSTOMER, method = RequestMethod.GET)
    public String showNewCustomerPage(Model model) throws Exception {

        log.info("Add new customer page");
        return PAGES + SLASH + CUSTOMER;
    }

    @RequestMapping(path = SLASH + EDIT_CUSTOMER, method = RequestMethod.POST)
    public String editCustomer(@Valid @ModelAttribute("customer") CustomerDto customerDto,
                                        BindingResult bindingResult, Model model,
                                        RedirectAttributes redirectAttributes) {

        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("error", environment.getProperty("error.form.invalidValues"));
            log.info("Wprowadzono niepoprawne wartosci do formularza edycji klienta");
            return "redirect:" + SLASH + EDIT_CUSTOMER;
        }
        try {
            customerService.save(customerDto);
            redirectAttributes.addFlashAttribute("info", environment.getProperty("message.customer.success"));
            log.info("Pomyślnie zedytowano klienta: " + customerDto.getUsername());
        } catch (Exception e){
            log.error("Nie udało się zedytować klienta " + customerDto.getName() + ": " + e);
        }
        return "redirect:" + SLASH + CUSTOMERS;
    }

    @RequestMapping(path = SLASH + CUSTOMER, method = RequestMethod.POST)
    public String registerCustomer(@Valid @ModelAttribute("customer") CustomerDto customerDto,
                                  BindingResult bindingResult, Model model,
                                  RedirectAttributes redirectAttributes) {

        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("info", environment.getProperty("error.form.invalidValues"));
            redirectAttributes.addFlashAttribute("alertType", "error");
            log.info("Wprowadzono niepoprawne wartosci do formularza dodawania klienta");
            return "redirect:" + SLASH + CUSTOMER;
        }
        try {
            customerService.update(customerDto);
            // environment.getProperty("message.customer.success")
            redirectAttributes.addFlashAttribute("info", environment.getProperty("message.customer.success"));
            redirectAttributes.addFlashAttribute("alertType", "success");
            log.info("Pomyślnie zedytowano klienta: " + customerDto.getUsername());
        } catch (Exception e){
            log.error("Nie udało się dodać użytkownika " + customerDto.getName() + "do bazy: " + e);

            redirectAttributes.addFlashAttribute("info", environment.getProperty("message.customer.error"));
            redirectAttributes.addFlashAttribute("alertType", "error");
            return "redirect:" + SLASH + CUSTOMERS;
        }

        return "redirect:" + SLASH + CUSTOMERS;
    }

    @RequestMapping(value = SLASH + CUSTOMERS, method = RequestMethod.GET)
    public String showCustomersList(Model model){

        model.addAttribute("customers", customerService.findAll());
        log.info("Lista klientów");
        return PAGES + SLASH + CUSTOMERS;
    }

    @RequestMapping(value = SLASH + EDIT_CUSTOMER + SLASH + "{id}", method = RequestMethod.GET)
    public String showEditCustomerPage(@PathVariable long id, Model model) {

        Customer customer = customerService.findById(id);
        model.addAttribute("customer", customer);
        log.info("Edycja klienta: " + customer.getUsername());
        return PAGES + SLASH + EDIT_CUSTOMER;
    }

    @RequestMapping(value = SLASH + DELETE_CUSTOMER, method = RequestMethod.POST)
    public String deleteCustomer(@RequestParam long id,
                                 Model model) {

        //TODO: komunikat o udanym/nieudanym usunieciu klienta
        customerService.delete(id);
        log.info("Usunięto użytkownika");
        return "redirect:" + SLASH + CUSTOMERS;
    }


}
