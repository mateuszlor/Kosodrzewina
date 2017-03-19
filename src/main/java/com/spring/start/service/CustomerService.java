package com.spring.start.service;

import com.spring.start.entity.Customer;
import com.spring.start.repository.CustomerRepository;
import com.spring.start.service.dto.CustomerDto;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Vertig0 on 18.03.2017.
 */
@Transactional
@Service
@Log4j
public class CustomerService {

    @Autowired
    @Getter @Setter
    private CustomerRepository customerRepository;

    public void createCustomer(CustomerDto customerDto) {
        Customer customer = Customer.builder().address(customerDto.getAddress())
                .name(customerDto.getName())
                .surname(customerDto.getSurname())
                .username(customerDto.getUsername())
                .phone(customerDto.getPhone())
                .build();
        customerRepository.save(customer);
        log.info("Dodano nowego klienta: " + customer.getUsername());
    }

    public void editCustomer(CustomerDto customerDto) {

        Customer customer = Customer.builder().address(customerDto.getAddress())
                .id(customerDto.getId())
                .name(customerDto.getName())
                .surname(customerDto.getSurname())
                .username(customerDto.getUsername())
                .phone(customerDto.getPhone())
                .build();
        customerRepository.save(customer);
        log.info("Dokonano edycji klienta: " + customer.getUsername());
    }

    public List<Customer> findAll(){
        return customerRepository.findAll();
    }

    public void deleteCustomer(long id) {
        try {
            customerRepository.delete(id);
        } catch (Exception e) {
            log.error("Wystąpił błąd przy usuwaniu klienta: " + e);
        }
    }

    public Customer findCustomerById(long id){
        Customer customer = customerRepository.findOne(id);
        return customer;
    }


}
