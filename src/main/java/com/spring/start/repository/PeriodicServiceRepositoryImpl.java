package com.spring.start.repository;

import com.spring.start.entity.PeriodicService;
import lombok.experimental.var;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Mateusz on 03.04.2017.
 */
@var
public class PeriodicServiceRepositoryImpl extends BaseAdditionalRepositoryImpl<PeriodicService> implements PeriodicServiceRepositoryAdditional {

    public List<PeriodicService> getServicesSoonToExpire(int days) {

        var date = new Date();

        var cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);
        var dateToCheck = cal.getTime();

        return getTable()
                .filter(s -> s.getDateTo().before(dateToCheck)
                        && s.getDateTo().after(date))
                .collect(Collectors.toList());
    }

    public long getServicesSoonToExpireCount(int days) {

        var date = new Date();

        var cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);
        var dateToCheck = cal.getTime();

        return getTable()
                .filter(s -> s.getDateTo().before(dateToCheck)
                        && s.getDateTo().after(date))
                .count();
    }

    @Override
    public List<PeriodicService> getServicesFromPeriodOfTime(Date from, Date to) {
        return getTable()
                .filter(ps -> ps.getDateFrom().after(from) && ps.getDateFrom().before(to))
                .collect(Collectors.toList());
    }
}
