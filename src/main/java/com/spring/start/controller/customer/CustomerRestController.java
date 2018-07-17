package com.spring.start.controller.customer;

import com.spring.start.annotations.RestAPIController;
import com.spring.start.entity.Customer;
import com.spring.start.service.CustomerService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestAPIController
@Log4j
public class CustomerRestController{

    private static final String GET_CUSTOMERS = "getCustomers";

    @Autowired
    @Getter
    @Setter
    private CustomerService customerService;

    @GetMapping(value = GET_CUSTOMERS)
    public List<Customer> getCustomersByRest(Model model){
        log.info("REST: Pobrano liste klientów");
       return customerService.findAllActive();
    }

}
