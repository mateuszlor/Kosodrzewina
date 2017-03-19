package com.spring.start.repository;

import com.spring.start.entity.User;
import org.springframework.data.repository.Repository;

/**
 * Created by Mateusz on 17.03.2017.
 */
public interface UserRepository extends Repository<User, Long> {
    User findByUsername(String username);
}
