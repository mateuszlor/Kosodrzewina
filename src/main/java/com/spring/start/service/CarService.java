package com.spring.start.service;

import com.spring.start.entity.Car;
import com.spring.start.entity.Customer;
import com.spring.start.repository.CarRepository;
import com.spring.start.service.dto.CarDto;
import com.spring.start.service.dto.CustomerDto;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Vertig0 on 22.03.2017.
 */
@Service
@Transactional
@Log4j
public class CarService {

    @Autowired
    @Getter @Setter
    private CarRepository carRepository;

    public void createCar(CarDto carDto){
        Car car = Car.builder()
                .brand(carDto.getBrand())
                .model(carDto.getModel())
                .name(carDto.getName())
                .registrationNumber(carDto.getRegistrationNumber())
                .build();
        carRepository.save(car);
        log.info("Dodano nowy samochód: " + car.getBrand() + " " + car.getModel() + "(" + car.getName() + ")");
    }

    public Iterable<Car> findAll(){
        return carRepository.findAll();
    }

    public void deleteCar(long id) {
        try {
            carRepository.delete(id);
        } catch (Exception e) {
            log.error("Wystąpił błąd przy usuwaniu klienta: " + e);
        }
    }

    public Car findCarById(long id){
        Car car = carRepository.findOne(id);
        return car;
    }

    public void editCar(CarDto carDto) {

        Car car = Car.builder().brand(carDto.getBrand())
                .id(carDto.getId())
                .name(carDto.getName())
                .model(carDto.getModel())
                .registrationNumber(carDto.getRegistrationNumber())
                .build();
        carRepository.save(car);
        log.info(String.format("Dokonano edycji samochodu: {0} {1}", carDto.getBrand(), carDto.getModel()));
    }

}
