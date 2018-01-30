package com.spring.start.controller.car;

import com.spring.start.entity.Car;
import com.spring.start.service.CarService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Log4j
public class CarRestController {
    //TODO: jakaś klasa nadrzędna czy coś?
    private static final String SLASH = "/";
    private static final String GET_CARS= "getCars";
    private static final String REST = "rest";

    @Autowired
    @Getter
    @Setter
    private CarService carService;

    @RequestMapping(value = SLASH + REST + SLASH + GET_CARS, method = RequestMethod.GET)
    public List<Car> getCarsByRest(Model model) {
        log.info("REST: Pobrano liste samochodów");
        return carService.findAllActive();
    }

}
