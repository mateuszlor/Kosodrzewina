package com.spring.start.controller.dictionary;


import com.spring.start.entity.Dictionary;
import com.spring.start.service.DictionaryService;
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
public class DictionaryRestController {

    private static final String SLASH = "/";
    private static final String GET_DICTIONARIES= "getDictionaries";
    private static final String REST = "rest";

    @Autowired
    @Getter
    @Setter
    private DictionaryService dictionaryService;

    @RequestMapping(value = SLASH + REST + SLASH + GET_DICTIONARIES, method = RequestMethod.GET)
    public List<Dictionary> getDictionariesByRest(Model model) {
        log.info("REST: Pobrano liste słowników serwisowych");
        return dictionaryService.findAllActive();
    }

}
