package com.spring.start.repository;

import com.spring.start.entity.Car;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Vertig0 on 22.03.2017.
 */
@Repository
public interface CarRepository extends CrudRepository<Car, Long>, CarRepositoryAdditional {
    Iterable<Car> findCarsByIsTrailerNotNull();
}
