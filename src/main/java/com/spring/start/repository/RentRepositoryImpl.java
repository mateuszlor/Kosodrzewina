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
    }

    @Override
    public List<Rent> findAllRentsWithoutAdditionalTrailer() {
        return getTable()
                .filter(r -> !getTable()
                        .filter(rt -> rt.getTrailer() != null)
                        .map(rt -> rt.getTrailer().getId())
                        .collect(Collectors.toList())
                        .contains(r.getId()))
                .collect(Collectors.toList());
    }
}
