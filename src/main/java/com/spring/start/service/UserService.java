package com.spring.start.service;

import com.spring.start.entity.Role;
import com.spring.start.entity.User;
import com.spring.start.helper.ControllerHelper;
import com.spring.start.interfaces.BasicDatabaseOperations;
import com.spring.start.repository.UserRepository;
import com.spring.start.service.dto.UserDto;
import com.spring.start.service.dto.ValidationUser;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.var;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Mateusz on 19.03.2017.
 */
@Transactional
@Service
@Log4j
@var
public class UserService implements BasicDatabaseOperations<User>{

    @Autowired
    @Getter
    @Setter
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private ControllerHelper controllerHelper;

    public void createUser(ValidationUser validationUser) {
        User user = User.builder()
                .name(validationUser.getName())
                .surname(validationUser.getSurname())
                .username(validationUser.getUsername())
                .password(bCryptPasswordEncoder.encode(validationUser.getPassword()))
                .email(validationUser.getEmail())
                .role(Role.ADMIN)
                .enabled(true)
                .build();
        userRepository.save(user);
        log.info("Dodano nowego użytkownika: " + user.getUsername());
    }

    @Override
    public void delete(long id) {
        userRepository.delete(id);
    }

    @Override
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(long id){
        return userRepository.findOne(id);
    }

    @Override
    public List<User> findAllActive() {
        return null;
    }

    public UserDto getUserDetails(String username) {
        var user = userRepository.findByUsername(username);
        var userDto = UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .username(username)
                .password(user.getPassword())
                .email(user.getEmail())
                .build();

        return userDto;
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }



    public void editUserData(UserDto dto) {

        var user = userRepository.findOne(dto.getId());

        user.builder()
            .name(dto.getName())
                .surname(dto.getSurname())
                .username(dto.getUsername())
                .build();

        userRepository.save(user);

        log.info(String.format("Saved data for user ID = %s", user.getId()));

        controllerHelper.forceReplaceUserInSession(dto);
    }

    public String getUserPassword(long id) {
        return userRepository.getUserPassword(id);
    }

    public void editUserPassword(UserDto dto) {
        var user = userRepository.findOne(dto.getId());

        var hash = bCryptPasswordEncoder.encode(dto.getNewPassword());

        user.builder()
                .password(hash)
                .build();

        userRepository.save(user);

        log.info(String.format("Saved password for user ID = %s", user.getId()));
    }

    public List<String> getEmails() {
        return userRepository.getEmails();
    }
}
