package com.spring.start.controller;

import com.spring.start.entity.Car;
import com.spring.start.helper.ControllerHelper;
import com.spring.start.service.CarService;
import com.spring.start.service.dto.CarDto;
import com.spring.start.validators.CarValidator;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * Created by Vertig0 on 21.03.2017.
 */
@Controller
@Log4j
public class CarController {

    private static final String SLASH = "/";
    private static final String PAGES = "pages";
    private static final String ADD_NEW_CAR = "new-car";
    private static final String CARS = "cars";
    private static final String DELETE_CAR = "delete-car";
    private static final String CAR = "car";

    @Autowired
    @Getter @Setter
    private CarService carService;

    @Autowired
    @Getter @Setter
    private CarValidator validator;

    @Autowired
    @Getter @Setter
    private Environment environment;

    @RequestMapping(value = SLASH + ADD_NEW_CAR, method = RequestMethod.GET)
    public String showNewCustomerPage(Model model) throws Exception {

        ControllerHelper.setUserData(model);

        log.info("Strona dodawania nowego samochodu");
        return PAGES + SLASH + ADD_NEW_CAR;
    }

    @RequestMapping(value = SLASH + ADD_NEW_CAR, method = RequestMethod.POST)
    public String addNewCar(@Valid @ModelAttribute("car") CarDto carDto,
                            BindingResult bindingResult, Model model,
                            RedirectAttributes redirectAttributes){

        validator.validate(carDto, bindingResult);
        if(bindingResult.hasErrors()){
//            redirectAttributes.addFlashAttribute("error", environment.getProperty("error.form.invalidValues"));
            log.info("Wprowadzono niepoprawne wartosci do formularza dodawania nowego samochodu");
            return "redirect:" + SLASH + ADD_NEW_CAR;
        }
        try {
            carService.createCar(carDto);
//            redirectAttributes.addFlashAttribute("info", environment.getProperty("message.customer.success"));
            log.info("Pomyślnie dodano samochód: " + carDto.getBrand() + " " + carDto.getModel());
        } catch (Exception e){
            log.error("Nie udało się dodać samochodu " + carDto.getBrand() + " " + carDto.getModel() + ": " + e);
        }
        return "redirect:" + SLASH + ADD_NEW_CAR;

    }

    @RequestMapping(value = SLASH + CARS, method = RequestMethod.GET)
    public String showCustomersList(Model model){

        ControllerHelper.setUserData(model);

        model.addAttribute("cars", carService.findAll());
        log.info("Lista samochodów");
        return PAGES + SLASH + CARS;
    }

    @RequestMapping(value = SLASH + DELETE_CAR, method = RequestMethod.POST)
    public String deleteCustomer(@RequestParam long id,
                                 Model model) {
        //TODO: czy jesteś pewien?

        //TODO: komunikat o udanym/nieudanym usunieciu klienta
        carService.deleteCar(id);
        log.info("Pomyślnie usunięto samochód");
        return "redirect:" + SLASH + CARS;
    }

    @RequestMapping(value = SLASH + CAR + SLASH + "{id}", method = RequestMethod.GET)
    public String showCarPanel(@PathVariable long id, Model model) {

        ControllerHelper.setUserData(model);

        Car car = carService.findCarById(id);
        model.addAttribute("car", car);
        log.info("Strona edycji samochodu: " + car.getBrand() + " " + car.getModel());
        return PAGES + SLASH + CAR;
    }


}
