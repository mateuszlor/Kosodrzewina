package com.spring.start.repository;

import com.spring.start.entity.PeriodicService;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Vertig0 on 30.03.2017.
 */
@Repository
public interface PeriodicServiceRepository extends CrudRepository<PeriodicService, Long>, PeriodicServiceRepositoryAdditional {
}
