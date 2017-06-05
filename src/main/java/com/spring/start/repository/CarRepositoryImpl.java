package com.spring.start.repository;

import com.spring.start.entity.Car;
import lombok.experimental.var;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Mateusz on 13.04.2017.
 */
@var
public class CarRepositoryImpl extends BaseAdditionalRepositoryImpl<Car> implements CarRepositoryAdditional {

    @Override
    public List<Car> findCarsByIds(List<Long> ids) {
        return getTable()
                .filter(c -> ids.contains(c.getId()))
                .collect(Collectors.toList());
    }
}
