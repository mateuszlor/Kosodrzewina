package com.spring.start.repository;

import com.spring.start.entity.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by Vertig0 on 05.06.2017.
 */
public interface ServiceRepositoryAdditional {

    List<Service> getServicesFromPeriodOfTime(Date from, Date to);

    /**
     * Returns services which dateTo is in less then {@param days} days
     * @param days Days to check
     * @return List of expiring services
     */
    List<Service> getServicesSoonToExpire(int days);

    /**
     * Returns number services which dateTo is in less then {@param days} days
     * @param days Days to check
     * @return Number of expiring services
     */
    long getServicesSoonToExpireCount(int days);

}
