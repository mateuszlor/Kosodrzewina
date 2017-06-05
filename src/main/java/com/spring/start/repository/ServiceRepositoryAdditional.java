package com.spring.start.repository;

import com.spring.start.entity.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by Vertig0 on 05.06.2017.
 */
public interface ServiceRepositoryAdditional {

    List<Service> getServicesFromPeriodOfTime(Date from, Date to);
}
