package com.spring.start.controller;

import com.spring.start.entity.Car;
import com.spring.start.service.CarService;
import com.spring.start.service.ServiceService;
import com.spring.start.service.dto.CarDto;
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
import java.util.stream.Collectors;

/**
 * Created by Vertig0 on 21.03.2017.
 */
@Controller
@Log4j
public class CarController extends BaseController{

    private static final String SLASH = "/";
    private static final String PAGES = "pages";
    private static final String ADD_NEW_CAR = "new-car";
    private static final String CARS = "cars";
    private static final String DELETE_CAR = "delete-car";
    private static final String CAR = "car";
    private static final String EDIT_CAR_URL = "edit";
    private static final String EDIT_CAR_HTML = "edit-car";
    private static final String DELETE_SERVICE = "delete-service";
    private static final String DELETE_PERIODIC = "delete-periodic";

    @Autowired
    @Getter @Setter
    private CarService carService;

    @Autowired
    @Getter @Setter
    private ServiceService service;

    @Autowired
    @Getter @Setter
    private Environment env;

    /**
     *  Metoda wyświatlajaca stronę dodawania nowego samochodu
     * */
    @RequestMapping(value = SLASH + ADD_NEW_CAR, method = RequestMethod.GET)
    public String showNewCarPage(Model model) throws Exception {

        log.info("Strona dodawania nowego samochodu");
        return PAGES + SLASH + ADD_NEW_CAR;
    }

    /**
     *  Metoda służąca do dodawania nowego samochodu do bazy
     * */
    @RequestMapping(value = SLASH + ADD_NEW_CAR, method = RequestMethod.POST)
    public String addNewCar(@Valid @ModelAttribute("car") CarDto carDto,
                            BindingResult bindingResult, Model model,
                            RedirectAttributes redirectAttributes){

        log.info("DODAWANIE NOWEGO SAMOCHODU");
        if(bindingResult.hasErrors()){
            addMessage(redirectAttributes, MessageType.ERROR, "message.car.add.error",
                    bindingResult.getFieldErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.toList()));
            log.info("Wprowadzono niepoprawne wartosci do formularza dodawania nowego samochodu");
            return "redirect:" + SLASH + ADD_NEW_CAR;
        }
        try {
            carService.save(carDto);
            addMessage(redirectAttributes, MessageType.SUCCESS, "message.car.add.success");
            log.info("Pomyślnie dodano samochód: " + carDto.getBrand() + " " + carDto.getModel());
        } catch (Exception e){
            log.error("Nie udało się dodać samochodu " + carDto.getBrand() + " " + carDto.getModel() + ": " + e);
        }
        return "redirect:" + SLASH + ADD_NEW_CAR;
    }

    /**
     *  Metoda wyświetlajaca stronę z listą samochodów
     * */
    @RequestMapping(value = SLASH + CARS, method = RequestMethod.GET)
    public String showCarList(Model model){

        model.addAttribute("cars", carService.findAllActive());
        log.info(String.format("Lista samochodów"));
        return PAGES + SLASH + CARS;
    }

    /**
     *  Metoda usuwająca samochody z bazy danych
     * */
    @RequestMapping(value = SLASH + DELETE_CAR, method = RequestMethod.POST)
    public String deleteCar(@RequestParam long id,
                            Model model, RedirectAttributes redirectAttributes) {
        try {
            addMessage(redirectAttributes, MessageType.SUCCESS, "message.car.delete.success");
            carService.delete(id);
            log.info("Pomyślnie usunięto samochód");
        } catch (Exception e) {
            log.error("Nie udało się usunąć samochodu: " + e);
        }
        return "redirect:" + SLASH + CARS;
    }

    /**
     *  Metoda wyświetlająca stronę samochodu
     * */
    @RequestMapping(value = SLASH + CAR + SLASH + "{id}", method = RequestMethod.GET)
    public String showCarPanel(@PathVariable long id, Model model) {

        Car car = carService.findById(id);
        model.addAttribute("car", car);
        model.addAttribute("service",  service.getServicesByCar(car));
        model.addAttribute("periodicService", service.getPeriodicServicesByCar(car));
        log.info(String.format("Strona samochodu: %1s %2s", car.getBrand(), car.getModel()));
        return PAGES + SLASH + CAR;
    }
    /**
     *  Metoda usuwająca dany wpis serwisowy
     **/
    @RequestMapping(value = SLASH + DELETE_SERVICE, method = RequestMethod.POST)
    public String deleteService(@RequestParam long id, @RequestParam long carId, RedirectAttributes redirectAttributes) {
        //TODO: czy jesteś pewien?

        service.delete(id);
        addMessage(redirectAttributes, MessageType.SUCCESS, "message.service.delete.success");
        log.info("Pomyślnie usunięto wpis serwisowy");
        return "redirect:" + SLASH + CAR + SLASH + carId;
    }

    /**
     *  Metoda wyświetlajaca stronę edycji samochodu
     * */
    @RequestMapping(value = SLASH + CAR + SLASH + "{id}" + SLASH + EDIT_CAR_URL, method = RequestMethod.GET)
    public String showCarEditPage(@PathVariable long id, Model model){

        Car car = carService.findById(id);
        model.addAttribute("car", car);
        log.info(String.format("Strona edycji: %1s %2s", car.getBrand(), car.getModel()));
        return PAGES + SLASH + EDIT_CAR_HTML;
    }

    /**
     * Metoda edytująca samochód
     * */
    @RequestMapping(path = SLASH + EDIT_CAR_HTML, method = RequestMethod.POST)
    public String editCar(@Valid @ModelAttribute("car") CarDto carDto,
                               BindingResult bindingResult, Model model,
                               RedirectAttributes redirectAttributes) {

        if(bindingResult.hasErrors()){
            addMessage(redirectAttributes, MessageType.ERROR, "error.form.invalidValues",
                    bindingResult.getFieldErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.toList()));
            log.info("Wprowadzono niepoprawne wartości do formularza edycji samochodu");
            return "redirect:" + SLASH + CAR + SLASH + carDto.getId() + SLASH + EDIT_CAR_URL;
        }
        try {
            carService.update(carDto);
            addMessage(redirectAttributes, MessageType.SUCCESS, "message.car.edit.success");
            log.info("Pomyślnie zedytowano samochód: " + carDto.getBrand());
        } catch (Exception e){
            addMessage(redirectAttributes, MessageType.ERROR, "message.car.edit.error");
            log.error(String.format("Nie udało się zedytować klienta %1s : %2s", carDto.getBrand(), e));
        }
        return "redirect:" + SLASH + CAR + SLASH + carDto.getId();
    }


}
