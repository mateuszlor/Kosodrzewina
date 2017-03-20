package com.spring.start.service;

import com.spring.start.entity.User;
import com.spring.start.helper.ControllerHelper;
import com.spring.start.repository.UserRepository;
import com.spring.start.service.dto.UserDto;
import lombok.extern.log4j.Log4j;
import lombok.experimental.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Mateusz on 19.03.2017.
 */
@Transactional
@Service
@Log4j
@var
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserDto getUserDetails(String username) {
        var user = userRepository.findByUsername(username);
        var userDto = UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .username(username)
                .password(user.getPassword())
                .build();

        return userDto;
    }

    public void editUserData(UserDto dto) {

        var user = userRepository.findOne(dto.getId());

        user.builder()
            .name(dto.getName())
                .surname(dto.getSurname())
                .username(dto.getUsername())
                .build();

        userRepository.save(user);

        log.info(String.format("Saved user ID = %s", user.getId()));

        ControllerHelper.forceReplaceUserInSession(dto);
    }
}
