package com.spring.start.service;

import com.spring.start.entity.Dictionary;
import com.spring.start.entity.DictionaryType;
import com.spring.start.repository.DictionaryRepository;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Vertig0 on 27.03.2017.
 */
@Service
@Transactional
@Log4j
public class DictionaryService {

    @Autowired
    @Getter
    @Setter
    private DictionaryRepository dictionaryRepository;

    public void addEntry(String name, DictionaryType type){
        Dictionary car = Dictionary.builder()
                .name(name)
                .type(type)
                .build();
        dictionaryRepository.save(car);
        log.info("Dodano nowy wpis słownikowy");
    }

    public Iterable<Dictionary> findAll(){
        return dictionaryRepository.findAll();
    }

    public void deleteEntry(long id) {
        try {
            dictionaryRepository.delete(id);
        } catch (Exception e) {
            log.error("Wystąpił błąd przy usuwaniu wpisu: " + e);
        }
    }

    public Dictionary findEntryById(long id){
        Dictionary entry = dictionaryRepository.findOne(id);
        return entry;
    }

    public void editEntry(long id, String name, DictionaryType type) {

        Dictionary dictionary = Dictionary.builder()
                .id(id)
                .name(name)
                .type(type)
                .build();
        dictionaryRepository.save(dictionary);
        log.info(String.format("Dokonano edycji wpisu"));
    }


}
