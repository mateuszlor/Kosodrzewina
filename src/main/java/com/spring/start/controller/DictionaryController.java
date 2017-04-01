package com.spring.start.controller;

import com.spring.start.entity.Dictionary;
import com.spring.start.entity.DictionaryType;
import com.spring.start.helper.ControllerHelper;
import com.spring.start.service.DictionaryService;
import com.spring.start.validators.DictionaryValidator;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Created by Vertig0 on 27.03.2017.
 */
@Controller
@Log4j
public class DictionaryController extends HandlerInterceptorAdapter{

    private static final String SLASH = "/";
    private static final String PAGES = "pages";
    private static final String DICTIONARY = "dictionary";
    private static final String DICTIONARIES = "dictionaries";
    private static final String DELETE_DICTIONARY = "delete-entry";
    private static final String EDIT_DICTIONARY = "edit-dictionary";

    @Autowired
    @Getter @Setter
    private DictionaryService dictionaryService;

    @Autowired
    @Getter
    @Setter
    private DictionaryValidator validator;


    /**
     *  Metoda wyświetlająca strone dodawania/edycji wpisu słownikowego
     * */
    @RequestMapping(value = SLASH + DICTIONARY, method = RequestMethod.GET)
    public String showDictionaryPage(Model model){

        log.info("Dodawanie nowego wpisu do słownika");
        return PAGES + SLASH + DICTIONARY;
    }

    /**
     *  Metoda dodająca nowy wpis do bazy
     * */
    @RequestMapping(value = SLASH + DICTIONARY, method = RequestMethod.POST)
    public String addNewDictionaryEntry(@ModelAttribute("name") String name,
                                       @ModelAttribute("type") DictionaryType type,
                                       BindingResult bindingResult, Model model,
                                       RedirectAttributes redirectAttributes){

        validator.validate(name, bindingResult);
        if(bindingResult.hasErrors()){
            log.info("Wprowadzono niepoprawne wartosci do formularza dodawania nowego wpisu słownikowego");
            return "redirect:" + SLASH + DICTIONARY;
        }
        try {
            dictionaryService.addEntry(name, type);
        } catch (Exception e){
            log.error("Nie udało się dodać nowego wpisu: " + e);
        }
        return PAGES + SLASH + DICTIONARY;
    }

    /**
     *  Metoda wyświetlająca stronę z listą wpisów słownikowych
     * */
    @RequestMapping(value = SLASH + DICTIONARIES, method = RequestMethod.GET)
    public String showDictionaryListPage(Model model){

        model.addAttribute("dictionaries", dictionaryService.findAll());
        log.info("Strona listy słownikowej");
        return PAGES + SLASH + DICTIONARIES;
    }


    /**
     *  Metoda usuwająca wpis słownikowy z bazy danych
     * */
    @RequestMapping(value = SLASH + DELETE_DICTIONARY, method = RequestMethod.POST)
    public String deleteEntry(@RequestParam long id,
                            Model model) {
        //TODO: czy jesteś pewien?

        //TODO: komunikat o udanym/nieudanym usunieciu klienta
        dictionaryService.deleteEntry(id);
        log.info("Pomyślnie usunięto wpis");
        return "redirect:" + SLASH + DICTIONARIES;
    }

    /**
     *  Metoda wyświetlajaca stronę edycji samochodu
     * */
    @RequestMapping(value = SLASH + DICTIONARY + SLASH + "{id}", method = RequestMethod.GET)
    public String showEditEntryPage(@PathVariable long id,
                                    Model model){

        Dictionary dictionary = dictionaryService.findEntryById(id);
        model.addAttribute("dictionary", dictionary);
        model.addAttribute("action", "edit");
        log.info(String.format("Strona edycji wpisu słownikowego"));
        return PAGES + SLASH + DICTIONARY;
    }

    /**
     * Metoda edytująca samochód
     * */
    @RequestMapping(path = SLASH + EDIT_DICTIONARY, method = RequestMethod.POST)
    public String editCar(@ModelAttribute("name") String name,
                          @ModelAttribute("type") DictionaryType type,
                          @ModelAttribute("id") long id,
                          BindingResult bindingResult, Model model,
                          RedirectAttributes redirectAttributes) {

        validator.validate(name, bindingResult);
        if(bindingResult.hasErrors()){
            log.info("Wprowadzono niepoprawne wartości podczas próby edycji wpisu");
            model.addAttribute("action", "edit");
            return "redirect:" + PAGES + SLASH + DICTIONARY;
        }
        try {
            dictionaryService.editEntry(id, name, type);
            log.info("Pomyślnie zedytowano wpis ");
        } catch (Exception e){
            log.error(String.format("Nie udało się zedytować wpisu %1s", e));
        }
        return "redirect:" + DICTIONARIES;
    }


}
