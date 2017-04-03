package com.spring.start.service;

import com.spring.start.entity.PeriodicService;
import com.spring.start.entity.Service;
import com.spring.start.repository.PeriodicServiceRepository;
import com.spring.start.repository.ServiceRepository;
import com.spring.start.service.dto.ServiceDto;
import com.spring.start.service.dto.UserDto;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Created by Vertig0 on 30.03.2017.
 */
@Transactional
@org.springframework.stereotype.Service
@Log4j
public class ServiceService {

    @Autowired
    @Getter
    @Setter
    private ServiceRepository serviceRepository;

    @Autowired
    @Getter
    @Setter
    private PeriodicServiceRepository periodicServiceRepository;

    @Autowired
    @Getter
    @Setter
    private DictionaryService dictionaryService;

    @Autowired
    @Getter
    @Setter
    private UserService userService;

    @Autowired
    @Getter
    @Setter
    private CarService carService;

    public void createService(ServiceDto serviceDto, UserDto userDto) {

        Service service = Service.builder()
                .car(carService.findCarById(serviceDto.getCar()))
                .type(dictionaryService.findEntryById(serviceDto.getName()))
                .execute(convertStringToDate(serviceDto.getDate()))
                .createdBy(userService.getUserById(userDto.getId()))
                .build();
        serviceRepository.save(service);
        log.info("Dodano nowy serwis");
    }

    public void createPeriodicService(ServiceDto serviceDto, UserDto userDto) {

        PeriodicService service = PeriodicService.builder()
                .car(carService.findCarById(serviceDto.getCar()))
                .type(dictionaryService.findEntryById(serviceDto.getName()))
                .dateFrom(convertStringToDate(serviceDto.getDate()))
                .dateTo(convertStringToDate(serviceDto.getDateTo()))
                .createdBy(userService.getUserById(userDto.getId()))
                .build();
        periodicServiceRepository.save(service);
        log.info("Dodano nowy serwis");
    }

    private Date convertStringToDate(String stringDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.ENGLISH);
        LocalDate date = LocalDate.parse(stringDate, formatter);
        return java.sql.Date.valueOf(date);
    }

    public void deleteService(long id) {
        try {
            serviceRepository.delete(id);
        } catch (Exception e) {
            log.error("Wystąpił błąd przy usuwaniu wpisu serwisowego: " + e);
        }
    }

    public void deletePeriodicService(long id) {
        try {
            periodicServiceRepository.delete(id);
        } catch (Exception e) {
            log.error("Wystąpił błąd przy usuwaniu okresowego wpisu serwisowego: " + e);
        }
    }

    public Service findServiceById(long id){
        try {
            if (serviceRepository.findOne(id) != null) {
                return serviceRepository.findOne(id);
            }else if (periodicServiceRepository.findOne(id) != null) {
//                return periodicServiceRepository.findOne(id);
            }
        } catch (Exception e) {
            log.error("Wystąpił problem przy pobieraniu serwisu, brak serwisu w bazie");
        }
        return null;
    }


}
