package com.spring.start.repository;

import com.spring.start.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Mateusz on 17.03.2017.
 */
    @Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
