package com.spring.start.repository;

import com.spring.start.entity.Car;

import java.util.List;

/**
 * Created by Mateusz on 13.04.2017.
 */
public interface CarRepositoryAdditional {

    List<Car> findCarsByIds(List<Long> ids);
}
