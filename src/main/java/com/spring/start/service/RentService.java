package com.spring.start.service;

import com.spring.start.entity.Dictionary;
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
import java.util.List;
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
                .startDate(Functions.convertStringToDateTime(rentDto.getStartDate()))
                .endDate(Functions.convertStringToDateTime(rentDto.getEndDate()))
                .income(rentDto.getIncome())
                .startCourse(rentDto.getStartCourse())
                .endCourse(rentDto.getEndCourse())
                .description(rentDto.getDescription())
                .status(rentDto.getStatus())
                .trailer(trailer)
                .build();

        Rent savedRent = rentRepository.save(rent);
        return savedRent;
    }

    @Override
    public List<Rent> findAllActive(){
        return rentRepository.findAllByDeletedFalse();
    }

    @Override
    public Rent findById(long id) {
        return rentRepository.findOne(id);
    }

    @Override
    public void delete(long id){

        try {
            Rent rent = rentRepository.findOne(id);
            rent.setDeleted(true);
            rentRepository.save(rent);
        } catch (Exception e) {
            log.error("Wystąpił błąd przy usuwaniu wyporzyczenia: " + e);
        }
    }

    @Override
    public Iterable<Rent> findAll() {
        return rentRepository.findAll();
    }

    public void returnRent(long id, String endDate, Long endCourse){
        Rent rent = rentRepository.findOne(id);
        rent.setEndDate(Functions.convertStringToDateTime(endDate));
        rent.setStatus(RentStatus.ENDED);
        //TODO: Zastanowić sie jak zrobić ten opis kilometrów końcowych
        if (endCourse != null) {
            rent.setEndCourse(endCourse);
        }
        rentRepository.save(rent);
        log.info("Pomyślnie zamknięto wyporzyczenie");
    }

    public void rent(long id) {
        Rent rent = rentRepository.findOne(id);
        rent.setStatus(RentStatus.RENTED);
        if (rent.getTrailer() != null) {
            rent.getTrailer().setStatus(RentStatus.RENTED);
        }
        update(rent);
    }

    //TODO FIX: Nie działa poprawnie metoda, do zmiany
    public Iterable<Rent> findAllRentsWithoutAdditionalCarCarrier() {
        return rentRepository.findAllRentsWithoutAdditionalTrailer();
    }

    public void update(Rent rent) {
        rentRepository.save(rent);
    }

}
