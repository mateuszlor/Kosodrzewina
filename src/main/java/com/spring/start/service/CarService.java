package com.spring.start.service;

import com.spring.start.entity.Car;
import com.spring.start.interfaces.BasicDatabaseOperations;
import com.spring.start.repository.CarRepository;
import com.spring.start.service.dto.CarDto;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.var;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Vertig0 on 22.03.2017.
 */
@Service
@Transactional
@Log4j
@var
public class CarService implements BasicDatabaseOperations<Car> {

    @Autowired
    @Getter @Setter
    private CarRepository carRepository;

    public void save(CarDto carDto) {
        Car car = Car.builder()
                .brand(carDto.getBrand())
                .model(carDto.getModel())
                .name(carDto.getName())
                .registrationNumber(carDto.getRegistrationNumber())
                .isTrailer(carDto.getIsTrailer())
                .build();
        carRepository.save(car);
        log.info("Dodano nowy samochód: " + car.getBrand() + " " + car.getModel() + "(" + car.getName() + ")");
    }

    @Override
    public Iterable<Car> findAll() {
        return carRepository.findAll();
    }

    @Override
    public void delete(long id) {
        try {
            carRepository.delete(id);
        } catch (Exception e) {
            log.error("Wystąpił błąd przy usuwaniu klienta: " + e);
        }
    }

    @Override
    public Car findById(long id) {
        Car car = carRepository.findOne(id);
        return car;
    }

    public void update(CarDto carDto) {

        Car car = Car.builder().brand(carDto.getBrand())
                .id(carDto.getId())
                .name(carDto.getName())
                .model(carDto.getModel())
                .registrationNumber(carDto.getRegistrationNumber())
                .build();
        carRepository.save(car);
        log.info(String.format("Dokonano edycji samochodu: {0} {1}", carDto.getBrand(), carDto.getModel()));
    }

    public Iterable<Car> findCarsByIsTrailer() {

        return carRepository.findCarsByIsTrailerNotNull();
    }

    public List<CarDto> findCarsByIdList(List<Long> ids) {

        var cars = carRepository.findCarsByIds(ids);

        return cars.stream()
                .map(c -> CarDto
                        .builder()
                        .brand(c.getBrand())
                        .model(c.getModel())
                        .id(c.getId())
                        .isTrailer(c.getIsTrailer())
                        .name(c.getName())
                        .registrationNumber(c.getRegistrationNumber())
                        .build())
                .collect(Collectors.toList());
    }

}
