package com.spring.start.repository;

import com.spring.start.entity.PeriodicService;

import java.util.List;

/**
 * Created by Mateusz on 03.04.2017.
 */
public interface PeriodicServiceRepositoryAdditional {
    /**
     * Returns services which dateTo is in less then {@param days} days
     * @param days Days to check
     * @return List of expiring services
     */
    List<PeriodicService> getServicesSoonToExpire(int days);
}
