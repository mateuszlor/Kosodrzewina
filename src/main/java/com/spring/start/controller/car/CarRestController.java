package com.spring.start.controller.car;

import com.spring.start.annotations.RestAPIController;
import com.spring.start.entity.Car;
import com.spring.start.service.CarService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Log4j
@RestAPIController
public class CarRestController {
    //TODO: jakaś klasa nadrzędna czy coś?
    private static final String GET_CARS= "getCars";

    @Autowired
    @Getter
    @Setter
    private CarService carService;

    @GetMapping(value = GET_CARS)
    public List<Car> getCarsByRest(Model model) {
        log.info("REST: Pobrano liste samochodów");
        return carService.findAllActive();
    }

}
