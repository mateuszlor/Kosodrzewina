package com.spring.start.repository;

import com.spring.start.entity.Rent;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Vertig0 on 09.04.2017.
 */
public interface RentRepository extends CrudRepository<Rent, Long>, RentRepositoryAdditional{

    List<Rent> findAllByDeletedFalse();

    @Query("select r1 from Rent r1, Rent r2 where (r1.id != r2.trailer OR (SELECT COUNT(r) FROM Rent r) = 1) AND r.deleted = 0")
    Iterable<Rent> findAllRentsWithoutAdditionalTrailer();
}
