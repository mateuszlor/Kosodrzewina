package com.spring.start.service;

import com.spring.start.entity.PeriodicService;
import com.spring.start.entity.Service;
import com.spring.start.repository.PeriodicServiceRepository;
import com.spring.start.repository.ServiceRepository;
import com.spring.start.service.dto.ServiceDto;
import com.spring.start.service.dto.UserDto;
import lombok.experimental.var;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Created by Vertig0 on 30.03.2017.
 */
@Transactional
@org.springframework.stereotype.Service
@Log4j
@var
public class ServiceService {

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private PeriodicServiceRepository periodicServiceRepository;

    @Autowired
    private DictionaryService dictionaryService;

    @Autowired
    private UserService userService;

    @Autowired
    private CarService carService;

    public void createService(ServiceDto serviceDto, UserDto userDto) {

        Service service = Service.builder()
                .car(carService.findCarById(serviceDto.getCar()))
                .type(dictionaryService.findEntryById(serviceDto.getName()))
                .execute(convertStringToDate(serviceDto.getDate()))
                .createdBy(userService.getUserById(userDto.getId()))
                .cost(serviceDto.getCost())
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
                .cost(serviceDto.getCost())
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
            log.error("Wystąpił błąd przy usuwaniu okresowego wpisu serwisowego: {}", e);
        }
    }

    public Service findServiceById(long id) {
        try {
            if (serviceRepository.findOne(id) != null) {
                return serviceRepository.findOne(id);
            }
        } catch (Exception e) {
            log.error("Wystąpił problem przy pobieraniu serwisu: {}", e);
        }
        return null;
    }

    public PeriodicService findPeriodicServiceById(long id) {
        try {
            if (periodicServiceRepository.findOne(id) != null) {
                return periodicServiceRepository.findOne(id);
            }
        } catch (Exception e) {
            log.error("Wystąpił problem przy pobieraniu okresowego serwisu: {}", e);
        }
        return null;
    }

    public List<ServiceDto> getServicesSoonToExpire(int days) {
        var data = periodicServiceRepository.getServicesSoonToExpire(days);

        return data.stream()
                .map(s -> ServiceDto
                        .builder()
                        .id(s.getId())
                        .car(s.getCar().getId())
                        .date(s.getDateFrom().toString())
                        .dateTo(s.getDateTo().toString())
                        .type(s.getType().getName())
                        .cost(s.getCost())
                        .isPeriodic(true)
                        .build())
                .collect(Collectors.toList());
    }

    public long getServicesSoonToExpireCount(int days) {
        return periodicServiceRepository.getServicesSoonToExpireCount(days);
    }

    public List<ServiceDto> getAllServices() {
        var periodic = periodicServiceRepository.findAll();
        var oneTime = serviceRepository.findAll();

        var all = StreamSupport.stream(periodic.spliterator(), false)
                .map(s -> ServiceDto
                        .builder()
                        .id(s.getId())
                        .car(s.getCar().getId())
                        .date(s.getDateFrom().toString())
                        .dateTo(s.getDateTo().toString())
                        .type(s.getType().getName())
                        .cost(s.getCost())
                        .isPeriodic(true)
                        .build())
                .collect(Collectors.toList());

        all.addAll(StreamSupport.stream(oneTime.spliterator(), false)
                .map(s -> ServiceDto
                        .builder()
                        .id(s.getId())
                        .car(s.getCar().getId())
                        .date(s.getExecute().toString())
                        .type(s.getType().getName())
                        .cost(s.getCost())
                        .isPeriodic(false)
                        .build())
                .collect(Collectors.toList()));

        return all;
    }
}
