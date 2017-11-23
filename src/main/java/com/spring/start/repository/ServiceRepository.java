package com.spring.start.repository;

import com.spring.start.entity.Car;
import com.spring.start.entity.Service;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Vertig0 on 30.03.2017.
 */
@Repository
public interface ServiceRepository extends CrudRepository<Service, Long>, ServiceRepositoryAdditional {

    Iterable<Service> getAllByCarAndDeletedFalse(Car car);

}
