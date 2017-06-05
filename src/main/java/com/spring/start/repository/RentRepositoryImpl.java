package com.spring.start.repository;

import com.spring.start.entity.Rent;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Vertig0 on 05.06.2017.
 */
public class RentRepositoryImpl extends BaseAdditionalRepositoryImpl<Rent> implements RentRepositoryAdditional {

    @Override
    public List<Rent> getRentsBetweenDates(Date from, Date to) {

        return getTable()
                .filter(r -> r.getStartDate().after(from)
                        && r.getEndDate().before(to))
                .collect(Collectors.toList());
    }
}
