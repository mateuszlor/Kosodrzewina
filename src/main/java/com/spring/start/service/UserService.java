package com.spring.start.service;

import com.spring.start.repository.UserRepository;
import com.spring.start.service.dto.UserDto;
import lombok.experimental.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Mateusz on 19.03.2017.
 */
@Transactional
@Service
@var
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserDto getUserDetails(String username) {
        var user = userRepository.findByUsername(username);
        var userDto = UserDto.builder()
                .name(user.getName())
                .surname(user.getSurname())
                .username(username)
                .build();

        return userDto;
    }
}
