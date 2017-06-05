package com.spring.start.repository;

import com.spring.start.entity.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Vertig0 on 05.06.2017.
 */
public class ServiceRepositoryImpl extends BaseAdditionalRepositoryImpl<Service> implements ServiceRepositoryAdditional {
    @Override
    public List<Service> getServicesFromPeriodOfTime(Date from, Date to) {
        return getTable()
                .filter(s -> s.getExecute().after(from) && s.getExecute().before(to))
                .collect(Collectors.toList());
    }
}
