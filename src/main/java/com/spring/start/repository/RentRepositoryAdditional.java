package com.spring.start.repository;

import com.spring.start.entity.Rent;

import java.util.Date;
import java.util.List;

/**
 * Created by Vertig0 on 05.06.2017.
 */
public interface RentRepositoryAdditional {

    /**
     * Returns rents from period of time
     * @param from start date
     * @param to end date
     * @return List of rents
     */
    List<Rent> getRentsBetweenDates(Date from, Date to);

    List<Rent> findAllRentsWithoutAdditionalTrailer();
}
