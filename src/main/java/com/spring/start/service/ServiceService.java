package com.spring.start.service;

import com.spring.start.entity.Car;
import com.spring.start.entity.DictionaryType;
import com.spring.start.entity.Service;
import com.spring.start.interfaces.BasicDatabaseOperations;
import com.spring.start.operations.Functions;
import com.spring.start.repository.ServiceRepository;
import com.spring.start.service.dto.ServiceDto;
import com.spring.start.service.dto.UserDto;
import lombok.experimental.var;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Created by Vertig0 on 30.03.2017.
 */
@Transactional
@org.springframework.stereotype.Service
@Log4j
@var
public class ServiceService implements BasicDatabaseOperations<Service> {

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private DictionaryService dictionaryService;

    @Autowired
    private UserService userService;

    @Autowired
    private CarService carService;

    public void createService(ServiceDto serviceDto, UserDto userDto) {

        Service service = Service.builder()
                .car(carService.findById(serviceDto.getCar()))
                .type(dictionaryService.findById(serviceDto.getName()))
                .executionDate(Functions.convertStringToDate(serviceDto.getDate()))
                .cost(serviceDto.getCost())
                .build();
        serviceRepository.save(service);
        log.info("Dodano nowy serwis");
    }

    public void createPeriodicService(ServiceDto serviceDto, UserDto userDto) {

        Service service = Service.builder()
                .car(carService.findById(serviceDto.getCar()))
                .type(dictionaryService.findById(serviceDto.getName()))
                .executionDate(Functions.convertStringToDate(serviceDto.getDate()))
                .endDate(Functions.convertStringToDate(serviceDto.getDateTo()))
                .cost(serviceDto.getCost())
                .build();
        serviceRepository.save(service);
        log.info("Dodano nowy serwis");
    }

    public void updateService(ServiceDto serviceDto) {
        try {
            Service service = serviceRepository.findOne(serviceDto.getId());
            service = Service.mergeUpdate(service, new Service(serviceDto));
            serviceRepository.save(service);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        log.info("Pomyślnie zaktualizowano serwis - SERWIS");
    }

    public void updatePeriodicService(ServiceDto serviceDto) {

    }

    @Override
    public void delete(long id) {
        try {
            Service service = serviceRepository.findOne(id);
            service.setDeleted(true);
            serviceRepository.save(service);
        } catch (Exception e) {
            log.error("Wystąpił błąd przy usuwaniu serwisu: " + e);
        }
    }

    @Override
    public Iterable<Service> findAll() {
        return serviceRepository.findAll();
    }

    public List<Service> findAllActive() {
        return serviceRepository.getAllByDeletedFalse();
    }

    public Service findById(long id) {
        try {
            if (serviceRepository.findOne(id) != null) {
                return serviceRepository.findOne(id);
            }
        } catch (Exception e) {
            log.error("Wystąpił problem przy pobieraniu serwisu: {}", e);
        }
        return null;
    }

    public Service findPeriodicServiceById(long id) {
        try {
            if (serviceRepository.findOne(id) != null) {
                return serviceRepository.findOne(id);
            }
        } catch (Exception e) {
            log.error("Wystąpił problem przy pobieraniu okresowego serwisu: {}", e);
        }
        return null;
    }

    public Iterable<Service> getServicesByCar(Car car){
        return serviceRepository.getAllByCarAndDeletedFalseAndTypeType(car, DictionaryType.SERVICE);
    }

    public Iterable<Service> getPeriodicServicesByCar(Car car){
        return serviceRepository.getAllByCarAndDeletedFalseAndTypeType(car, DictionaryType.PAYMENT);
    }

    public List<ServiceDto> getServicesSoonToExpire(int days) {
        var data = serviceRepository.getServicesSoonToExpire(days);

        return data.stream()
                .map(s -> ServiceDto
                        .builder()
                        .id(s.getId())
                        .car(s.getCar().getId())
                        .date(s.getExecutionDate().toString())
                        .dateTo(s.getEndDate().toString())
                        .type(s.getType().getName())
                        .cost(s.getCost())
                        .isPeriodic(true)
                        .build())
                .collect(Collectors.toList());
    }

    public long getServicesSoonToExpireCount(int days) {
        return serviceRepository.getServicesSoonToExpireCount(days);
    }

    public List<ServiceDto> getAllServices() {
        //TODO: zmienić na specjalne metody z typem
        var periodic = serviceRepository.findAll();
        var oneTime = serviceRepository.findAll();

        var all = StreamSupport.stream(periodic.spliterator(), false)
                .map(s -> ServiceDto
                        .builder()
                        .id(s.getId())
                        .car(s.getCar().getId())
                        .date(s.getExecutionDate().toString())
                        .dateTo(s.getEndDate().toString())
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
                        .date(s.getExecutionDate().toString())
                        .type(s.getType().getName())
                        .cost(s.getCost())
                        .isPeriodic(false)
                        .build())
                .collect(Collectors.toList()));

        return all;
    }
}
