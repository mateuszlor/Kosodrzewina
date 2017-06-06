package com.spring.start.service;

import com.spring.start.entity.Rent;
import com.spring.start.entity.RentStatus;
import com.spring.start.interfaces.BasicDatabaseOperations;
import com.spring.start.operations.Functions;
import com.spring.start.repository.RentRepository;
import com.spring.start.service.dto.RentDto;
import com.spring.start.service.dto.UserDto;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Vertig0 on 09.04.2017.
 */
@Transactional
@Service
@Log4j
public class RentService implements BasicDatabaseOperations<Rent>{

    @Autowired
    @Getter
    @Setter
    private RentRepository rentRepository;

    @Autowired
    @Getter
    @Setter
    private UserService userService;

    @Autowired
    @Getter
    @Setter
    private CarService carService;

    @Autowired
    @Getter
    @Setter
    private CustomerService customerService;



    public Rent addRent(RentDto rentDto, UserDto user, Rent trailer) {

        Rent rent = Rent.builder()
                .car(carService.findById(rentDto.getCar()))
                .customer(customerService.findById(rentDto.getCustomer()))
                .startDate(Functions.convertStringToDate(rentDto.getStartDate()))
                .endDate(Functions.convertStringToDate(rentDto.getEndDate()))
                .income(rentDto.getIncome())
                .startCourse(rentDto.getStartCourse())
                .endCourse(rentDto.getEndCourse())
                .description(rentDto.getDescription())
                .createdBy(userService.findById(user.getId()))
                .status(rentDto.getStatus())
                .trailer(trailer)
                .build();

        Rent savedRent = rentRepository.save(rent);
        return savedRent;
    }

    @Override
    public Iterable<Rent> findAll(){
        return rentRepository.findAll();
    }

    @Override
    public Rent findById(long id) {
        return rentRepository.findOne(id);
    }

    @Override
    public void delete(long id){
        rentRepository.delete(id);
    }

    public void returnRent(long id, String endDate, Long endCourse){
        Rent rent = rentRepository.findOne(id);
        rent.setEndDate(Functions.convertStringToDate(endDate));
        rent.setStatus(RentStatus.ENDED);
        if (endCourse != null) {
            rent.setEndCourse(endCourse);
        }
        rentRepository.save(rent);
        log.info("Pomyślnie zamknięto wyporzyczenie");
    }

    public void update(Rent rent) {
        rentRepository.save(rent);
    }

}
