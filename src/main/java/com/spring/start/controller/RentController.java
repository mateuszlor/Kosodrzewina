package com.spring.start.controller;

import com.spring.start.entity.Car;
import com.spring.start.entity.DictionaryType;
import com.spring.start.entity.Rent;
import com.spring.start.service.CarService;
import com.spring.start.service.CustomerService;
import com.spring.start.service.RentService;
import com.spring.start.service.dto.RentDto;
import com.spring.start.service.dto.UserDto;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.math.BigDecimal;

/**
 * Created by Vertig0 on 09.04.2017.
 */
@Controller
@Log4j
public class RentController {

    private static final String SLASH = "/";
    private static final String PAGES = "pages";
    private static final String ADD_RENT = "add-rent";
    private static final String RENTS = "rents";
    private static final String DELETE_RENT = "delete-rent";
    private static final String RETURN_RENT = "return-rent";

    @Autowired
    private RentService rentService;

    @Autowired
    private CarService carService;

    @Autowired
    private CustomerService customerService;

//    @Autowired
//    private RentValidator validator;


    @RequestMapping(path = SLASH + ADD_RENT, method = RequestMethod.GET)
    public String showAddRentPage(Model model) {

        model.addAttribute("trailers", carService.findCarsByIsTrailer());
        model.addAttribute("cars", carService.findAll());
        model.addAttribute("customers", customerService.findAll());
        log.info("Strona dodawania nowego wypożyczenia");
        return PAGES + SLASH + ADD_RENT;
    }

    @RequestMapping(path = SLASH + ADD_RENT, method = RequestMethod.POST)
    public String addRent(@Valid @ModelAttribute("rent") RentDto rent,
                          BindingResult bindingResult, Model model,
                          RedirectAttributes redirectAttributes,
                          HttpServletRequest request) {
        //TODO: Validator
//        validator.validate(rent, bindingResult);
//        if (bindingResult.hasErrors()) {
//            log.info("Wprowadzono niepoprawne wartosci do formularza dodawania wyporzyczenia");
//            return "redirect:" + SLASH + ADD_RENT;
//        }


        try {
            if (rent.getIsTrailer()) {
                RentDto trailerRent = new RentDto(rent);
                trailerRent.setIncome(rent.getAdditionalIncome());
                trailerRent.setCar(rent.getTrailer());
                //TODO: zrobić powiązanie lawety z samochodem jeśli razem
                rentService.addRent(trailerRent, (UserDto) request.getSession().getAttribute("user"), null);
            }
            rentService.addRent(rent, (UserDto) request.getSession().getAttribute("user"), null);
            log.info("Pomyślnie dodano wyporzyczenie");
        } catch (Exception e) {
            log.error("Nie udało się dodać wyporzyczenia: " + e);
        }
        return "redirect:" + SLASH + ADD_RENT;
    }

    @RequestMapping(path = SLASH + RENTS, method = RequestMethod.GET)
    public String showRentsPage(Model model) {

        model.addAttribute("rents", rentService.findAll());
        log.info("Lista wyporzyczeń");
        return PAGES + SLASH + RENTS;
    }

    /**
     *  Metoda usuwająca wpis słownikowy z bazy danych
     * */
    @RequestMapping(value = SLASH + DELETE_RENT, method = RequestMethod.POST)
    public String deleteRent(@RequestParam long id,
                              Model model) {

        //TODO: komunikat o udanym/nieudanym usunieciu klienta
        rentService.delete(id);
        log.info("Pomyślnie usunięto wyporzyczenie");
        return "redirect:" + SLASH + RENTS;
    }

    @RequestMapping(value = SLASH + RETURN_RENT, method = RequestMethod.POST)
    public String returnRent(@RequestParam long id,
                             @RequestParam String endDate,
                             @RequestParam Long endCourse,
                              Model model) {

        try {
            rentService.returnRent(id, endDate, endCourse);
            log.info("Pomyślnie dokonano zamknięcia wypożyczenia");
        } catch (Exception e){
            log.error("Zamkniecie wypożyczenia zakończyło się niepowodzeniem: {}", e);
        }
        return "redirect:" + SLASH + RENTS;
    }

}
