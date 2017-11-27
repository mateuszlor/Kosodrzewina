package com.spring.start.repository;

import com.spring.start.entity.Rent;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Vertig0 on 09.04.2017.
 */
public interface RentRepository extends CrudRepository<Rent, Long>, RentRepositoryAdditional{

    List<Rent> findAllByDeletedFalse();
}
