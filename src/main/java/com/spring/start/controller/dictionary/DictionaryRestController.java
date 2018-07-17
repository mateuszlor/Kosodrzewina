package com.spring.start.controller.dictionary;


import com.spring.start.annotations.RestAPIController;
import com.spring.start.entity.Dictionary;
import com.spring.start.service.DictionaryService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RestAPIController
@Log4j
public class DictionaryRestController {

    private static final String GET_DICTIONARIES= "getDictionaries";

    @Autowired
    @Getter
    @Setter
    private DictionaryService dictionaryService;

    @GetMapping(value = GET_DICTIONARIES)
    public List<Dictionary> getDictionariesByRest(Model model) {
        log.info("REST: Pobrano liste słowników serwisowych");
        return dictionaryService.findAllActive();
    }

}
