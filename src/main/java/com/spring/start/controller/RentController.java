package com.spring.start.controller;

import com.spring.start.entity.Rent;
import com.spring.start.entity.RentStatus;
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
import java.util.stream.Collectors;

/**
 * Created by Vertig0 on 09.04.2017.
 */
@Controller
@Log4j
public class RentController extends BaseController{

    private static final String ADD_RENT = "add-rent";
    private static final String EDIT_RENT = "edit";
    private static final String RENTS = "rents";
    private static final String RENT = "rent";
    private static final String DELETE_RENT = "delete-rent";
    private static final String RETURN_RENT = "return-rent";
    private static final String CHAGNE_STATUS = "rent-rent";

    @Autowired
    private RentService rentService;

    @Autowired
    private CarService carService;

    @Autowired
    private CustomerService customerService;


    @RequestMapping(path = SLASH + ADD_RENT, method = RequestMethod.GET)
    public String showAddRentPage(Model model) {

        model.addAttribute("trailers", carService.findCarsByIsTrailer());
        model.addAttribute("cars", carService.findAllActive());
        model.addAttribute("customers", customerService.findAllActive());
        log.info("Strona dodawania nowego wypożyczenia");
        return PAGES + SLASH + ADD_RENT;
    }

    @RequestMapping(path = SLASH + ADD_RENT, method = RequestMethod.POST)
    public String addRent(@Valid @ModelAttribute("rent") RentDto rent,
                          BindingResult bindingResult, Model model,
                          RedirectAttributes redirectAttributes,
                          HttpServletRequest request) {

        if (bindingResult.hasErrors()) {
            addMessage(redirectAttributes, MessageType.ERROR, "message.rent.add.error.validator",
                    bindingResult.getFieldErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.toList()));
            log.info("Wprowadzono niepoprawne wartosci do formularza dodawania wyporzyczenia");
            return "redirect:" + SLASH + ADD_RENT;
        }

        try {
            Rent savedTraier = null;
            if (rent.getIsTrailer()) {
                RentDto trailerRent = new RentDto(rent);
                trailerRent.setIncome(rent.getAdditionalIncome());
                trailerRent.setCar(rent.getTrailer());
                savedTraier = rentService.addRent(trailerRent, (UserDto) request.getSession().getAttribute("user"), null);
            }
            rentService.addRent(rent, (UserDto) request.getSession().getAttribute("user"),
                    savedTraier != null ? savedTraier : null);
            addMessage(redirectAttributes, MessageType.SUCCESS, "message.rent.add.success");
            log.info("Pomyślnie dodano wyporzyczenie");
        } catch (Exception e) {
            addMessage(redirectAttributes, MessageType.ERROR, "message.rent.add.error");
            log.error("Nie udało się dodać wyporzyczenia: " + e);
        }
        return "redirect:" + SLASH + ADD_RENT;
    }

    @RequestMapping(path = SLASH + RENTS, method = RequestMethod.GET)
    public String showRentsPage(Model model) {

        model.addAttribute("rents", rentService.findAllRentsWithoutAdditionalCarCarrier());
        log.info("Lista wyporzyczeń");
        return PAGES + SLASH + RENTS;
    }


    //TODO: Strona podglądu a potem edycji
    /**
     *  Metoda wyświetlajaca stronę edycji samochodu
     * */
    @RequestMapping(value = SLASH + RENT + SLASH + "{id}" + SLASH + EDIT_RENT, method = RequestMethod.GET)
    public String showCarEditPage(@PathVariable long id, Model model){

        Rent rent = rentService.findById(id);
        model.addAttribute("rent", rent);
        log.info("Strona edycji wyporzyczenia");
        return PAGES + SLASH + ADD_RENT;
    }

    @RequestMapping(value = SLASH + RENT + SLASH + "{id}", method = RequestMethod.GET)
    public String showRentPage(@PathVariable long id, Model model) {
        Rent rent = rentService.findById(id);
        model.addAttribute("rent", rent);
        log.info("Strona wyporzyczenia");
        return PAGES + SLASH + RENT;
    }

    /**
     *  Metoda usuwająca wyporzyczenie z bazy danych
     * */
    @RequestMapping(value = SLASH + DELETE_RENT, method = RequestMethod.POST)
    public String deleteRent(@RequestParam long id,
                              RedirectAttributes redirectAttributes) {
        rentService.delete(id);
        addMessage(redirectAttributes, MessageType.SUCCESS, "message.rent.delete.success");
        log.info("Pomyślnie usunięto wyporzyczenie");
        return "redirect:" + SLASH + RENTS;
    }

    /**
     * Metoda zwracająca wyporzyczenie
     * */
    @RequestMapping(value = SLASH + RETURN_RENT, method = RequestMethod.POST)
    public String returnRent(@RequestParam long id,
                             @RequestParam String endDate,
                             @RequestParam Long endCourse,
                             @RequestParam(defaultValue="false") Boolean isTrailer,
                             Model model) {

        try {
            if (isTrailer) {
                Rent trailer = rentService.findById(id).getTrailer();
                if (trailer != null) {
                    rentService.returnRent(trailer.getId(), endDate, endCourse);
                }
            }
            rentService.returnRent(id, endDate, endCourse);
            log.info("Pomyślnie dokonano zamknięcia wypożyczenia");
        } catch (Exception e){
            log.error("Zamkniecie wypożyczenia zakończyło się niepowodzeniem: {}", e);
        }
        return "redirect:" + SLASH + RENTS;
    }

    @RequestMapping(value = SLASH + CHAGNE_STATUS, method = RequestMethod.POST)
    public String changeStatusToRented(@RequestParam long id, RedirectAttributes redirectAttributes) {
        try {
            Rent rent = rentService.findById(id);
            rent.setStatus(RentStatus.RENTED);
            rentService.update(rent);
            addMessage(redirectAttributes, MessageType.SUCCESS, "message.rent.changeStatus.success");
            log.info("Pomyślnie zaktualizowano status wyporzyczenia " + id);
        } catch (Exception e) {
            addMessage(redirectAttributes, MessageType.ERROR, "message.rent.changeStatus.error");
            log.error("Wystąpił problem przy zmianie statusu wyporzyczenia {}", e);
        }
        return "redirect:" + SLASH + RENTS;
    }

}
