package com.spring.start.repository;

import com.spring.start.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Vertig0 on 18.03.2017.
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{

    Customer findByUsername(String username);
}
