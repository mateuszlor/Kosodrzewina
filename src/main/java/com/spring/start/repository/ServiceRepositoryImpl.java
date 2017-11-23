package com.spring.start.repository;

import com.spring.start.entity.Service;
import lombok.experimental.var;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Vertig0 on 05.06.2017.
 */
@var
public class ServiceRepositoryImpl extends BaseAdditionalRepositoryImpl<Service> implements ServiceRepositoryAdditional {

    @Override
    public List<Service> getServicesFromPeriodOfTime(Date from, Date to) {
        return getTable()
                .filter(s -> s.getExecutionDate().after(from) && s.getExecutionDate().before(to))
                .collect(Collectors.toList());
    }

    public List<Service> getServicesSoonToExpire(int days) {

        var date = new Date();

        var cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);
        var dateToCheck = cal.getTime();

        return getTable()
                .filter(s -> s.getEndDate().before(dateToCheck)
                        && s.getEndDate().after(date))
                .collect(Collectors.toList());
    }

    public long getServicesSoonToExpireCount(int days) {

        var date = new Date();

        var cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);
        var dateToCheck = cal.getTime();

        return getTable()
                .filter(s -> s.getEndDate().before(dateToCheck)
                        && s.getEndDate().after(date))
                .count();
    }

}
