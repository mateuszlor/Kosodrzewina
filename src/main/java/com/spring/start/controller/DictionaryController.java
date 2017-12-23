package com.spring.start.controller;

import com.spring.start.entity.Dictionary;
import com.spring.start.entity.DictionaryType;
import com.spring.start.service.DictionaryService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.stream.Collectors;

/**
 * Created by Vertig0 on 27.03.2017.
 */
@Controller
@Log4j
public class DictionaryController extends BaseController{

    private static final String SLASH = "/";
    private static final String PAGES = "pages";
    private static final String DICTIONARY = "dictionary";
    private static final String DICTIONARIES = "dictionaries";
    private static final String DELETE_DICTIONARY = "delete-entry";
    private static final String EDIT_DICTIONARY = "edit-dictionary";

    @Autowired
    @Getter @Setter
    private DictionaryService dictionaryService;

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

        if(bindingResult.hasErrors()){
            addMessage(redirectAttributes, MessageType.ERROR, "message.dictionary.add.error.validator",
                    bindingResult.getFieldErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.toList()));
            log.info("Wprowadzono niepoprawne wartosci do formularza dodawania nowego wpisu słownikowego");
            return "redirect:" + SLASH + DICTIONARY;
        }
        try {
            dictionaryService.saveDictionary(name, type);
            addMessage(redirectAttributes, MessageType.SUCCESS, "message.dictionary.add.success");
            log.info("Pomyślnie dodano nowy wpis słownikowy");
        } catch (Exception e){
            addMessage(redirectAttributes, MessageType.ERROR, "message.dictionary.add.error");
            log.error("Nie udało się dodać nowego wpisu: " + e);
        }
        return "redirect:" + SLASH + DICTIONARIES;
    }

    /**
     *  Metoda wyświetlająca stronę z listą wpisów słownikowych
     * */
    @RequestMapping(value = SLASH + DICTIONARIES, method = RequestMethod.GET)
    public String showDictionaryListPage(Model model){
        model.addAttribute("dictionaries", dictionaryService.findAllActive());
        log.info("Strona listy słownikowej");
        return PAGES + SLASH + DICTIONARIES;
    }


    /**
     *  Metoda usuwająca wpis słownikowy z bazy danych
     * */
    @RequestMapping(value = SLASH + DELETE_DICTIONARY, method = RequestMethod.POST)
    public String deleteEntry(@RequestParam long id,
                              RedirectAttributes redirectAttributes) {
        try {
            dictionaryService.delete(id);
            addMessage(redirectAttributes, MessageType.SUCCESS, "message.dictionary.delete.success");
            log.info("Pomyślnie usunięto wpis");
        } catch (Exception e) {
            addMessage(redirectAttributes, MessageType.ERROR, "message.dictionary.delete.error");
            log.error("Wystąpił problem z usunieciem słownika z bazy: " + e);
        }
        return "redirect:" + SLASH + DICTIONARIES;
    }

    /**
     *  Metoda wyświetlajaca stronę edycji samochodu
     * */
    @RequestMapping(value = SLASH + DICTIONARY + SLASH + "{id}", method = RequestMethod.GET)
    public String showEditEntryPage(@PathVariable long id,
                                    Model model){
        Dictionary dictionary = dictionaryService.findById(id);
        model.addAttribute("dictionary", dictionary);
        model.addAttribute("action", "edit");
        log.info(String.format("Strona edycji wpisu słownikowego"));
        return PAGES + SLASH + DICTIONARY;
    }

    /**
     * Metoda edytująca samochód
     * */
    @RequestMapping(path = SLASH + EDIT_DICTIONARY, method = RequestMethod.POST)
    public String editEntry(@ModelAttribute("name") String name,
                          @ModelAttribute("type") DictionaryType type,
                          @ModelAttribute("id") long id,
                          BindingResult bindingResult, Model model,
                          RedirectAttributes redirectAttributes) {
        if(bindingResult.hasErrors()){
            addMessage(redirectAttributes, MessageType.ERROR, "message.dictionary.edit.error.validator",
                    bindingResult.getFieldErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.toList()));
            log.info("Wprowadzono niepoprawne wartości podczas próby edycji wpisu");
            model.addAttribute("action", "edit");
            return "redirect:" + SLASH + DICTIONARY + SLASH + id;
        }
        try {
            dictionaryService.edit(id, name, type);
            addMessage(redirectAttributes, MessageType.SUCCESS, "message.dictionary.edit.success");
            log.info("Pomyślnie zedytowano wpis ");
        } catch (Exception e){
            addMessage(redirectAttributes, MessageType.ERROR, "message.dictionary.delete.error");
            log.error(String.format("Nie udało się zedytować wpisu %1s", e));
        }
        return "redirect:" + DICTIONARIES;
    }


}
