package com.spring.start.repository;

import com.spring.start.entity.Car;
import com.spring.start.entity.DictionaryType;
import com.spring.start.entity.Service;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Vertig0 on 30.03.2017.
 */
@Repository
public interface ServiceRepository extends CrudRepository<Service, Long>, ServiceRepositoryAdditional {

    Iterable<Service> getAllByCarAndDeletedFalse(Car car);

    Iterable<Service> getAllByCarAndDeletedFalseAndTypeType(Car car, DictionaryType type);

    List<Service> getAllByDeletedFalse();

}
