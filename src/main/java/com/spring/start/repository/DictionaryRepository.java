package com.spring.start.repository;

import com.spring.start.entity.Dictionary;
import com.spring.start.entity.DictionaryType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Vertig0 on 22.03.2017.
 */
@Repository
public interface DictionaryRepository extends CrudRepository<Dictionary, Long> {
    Iterable<Dictionary> getDictionariesByType(DictionaryType type);
}
