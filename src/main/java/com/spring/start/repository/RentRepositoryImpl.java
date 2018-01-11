package com.spring.start.repository;

import com.spring.start.entity.Rent;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
                .filter(r -> from.before(r.getStartDate())
                        && to.after(r.getEndDate()))
                .collect(Collectors.toList());
//        return getTable()
//                .filter(r -> r.getStartDate().isAfter(LocalDateTime.parse(from.toString()))
//                        && r.getEndDate().isBefore(LocalDateTime.parse(to.toString())))
//                .collect(Collectors.toList());
    }
}
