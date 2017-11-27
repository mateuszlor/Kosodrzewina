package com.spring.start.service;

import com.spring.start.entity.Car;
import com.spring.start.entity.Customer;
import com.spring.start.interfaces.BasicDatabaseOperations;
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
public class CustomerService implements BasicDatabaseOperations<Customer>{

    @Autowired
    @Getter @Setter
    private CustomerRepository customerRepository;

    public void save(CustomerDto customerDto) {
        Customer customer = Customer.builder().address(customerDto.getAddress())
                .name(customerDto.getName())
                .surname(customerDto.getSurname())
                .username(customerDto.getUsername())
                .phone(customerDto.getPhone())
                .build();
        customerRepository.save(customer);
        log.info("Dodano nowego klienta: " + customer.getUsername());
    }

    public void update(CustomerDto customerDto) {

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

    public List<Customer> findAllActive(){
        return customerRepository.findAllByDeletedFalse();
    }

    @Override
    public void delete(long id) {
        try {
            Customer customer = customerRepository.findOne(id);
            customer.setDeleted(true);
            customerRepository.save(customer);
        } catch (Exception e) {
            log.error("Wystąpił błąd przy usuwaniu klienta: " + e);
        }
    }

    @Override
    public Iterable<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer findById(long id){
        Customer customer = customerRepository.findOne(id);
        return customer;
    }


}
