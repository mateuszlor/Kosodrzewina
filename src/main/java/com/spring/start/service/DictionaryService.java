package com.spring.start.service;

import com.spring.start.entity.Dictionary;
import com.spring.start.entity.DictionaryType;
import com.spring.start.interfaces.BasicDatabaseOperations;
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
public class DictionaryService implements BasicDatabaseOperations<Dictionary>{

    @Autowired
    @Getter
    @Setter
    private DictionaryRepository dictionaryRepository;

    public void saveDictionary(String name, DictionaryType type){
        Dictionary car = Dictionary.builder()
                .name(name)
                .type(type)
                .build();
        dictionaryRepository.save(car);
        log.info("Dodano nowy wpis słownikowy");
    }

    @Override
    public Iterable<Dictionary> findAll(){
        return dictionaryRepository.findAll();
    }

    public Iterable<Dictionary> getDictionaryiesByType(DictionaryType type){
        return dictionaryRepository.getDictionariesByType(type);
    }

    @Override
    public void delete(long id) {
        try {
            dictionaryRepository.delete(id);
        } catch (Exception e) {
            log.error("Wystąpił błąd przy usuwaniu wpisu: " + e);
        }
    }

    @Override
    public Dictionary findById(long id){
        Dictionary entry = dictionaryRepository.findOne(id);
        return entry;
    }

    public void edit(long id, String name, DictionaryType type) {
        try {
            Dictionary dictionary = dictionaryRepository.findOne(id);
            dictionary = Dictionary.mergeUpdate(dictionary, Dictionary.builder().name(name).type(type).build());
            dictionaryRepository.save(dictionary);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        log.info(String.format("Dokonano edycji wpisu"));
    }

}
