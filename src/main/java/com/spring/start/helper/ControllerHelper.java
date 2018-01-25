package com.spring.start.helper;

import com.spring.start.service.UserService;
import com.spring.start.service.dto.UserDto;
import lombok.Getter;
import lombok.experimental.var;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Collections;

/**
 * Created by Mateusz on 19.03.2017.
 */
@var
@Log4j
@Component
public class ControllerHelper {

    @Autowired
    private UserService userService;

    @Autowired
    private HttpServletRequest request;

    private static final String userKey = "user";

    public void setUserData(Model model) {

        log.info(String.format("About to set user data: request = %s, userService = %s, securityContext = %s",
                request,
                userService,
                SecurityContextHolder.getContext().getAuthentication()));

        var session = request.getSession();
        var userKey = "user";
        UserDto user;

        var isValidUserInfoInSession = Collections.list(session.getAttributeNames())
                .stream()
                .anyMatch(n -> n.equals(userKey))
                && ((UserDto) session.getAttribute(userKey))
                .getId() != -1;

        log.info("isValidUserInfoInSession: " + isValidUserInfoInSession);

        if (isValidUserInfoInSession) {
            user = (UserDto) session.getAttribute(userKey);
        } else {
            var auth = SecurityContextHolder.getContext().getAuthentication();

            if (auth == null) {
                log.warn("No authentication data!");

                user = UserDto.builder()
                        .id(-1)
                        .name("UNKNOWN")
                        .surname("")
                        .username("???")
                        .build();
            } else {
                var springUser = (User) auth.getPrincipal();

                log.info("Got user data from spring security: " + springUser);

                user = userService.getUserDetails(springUser.getUsername());
            }

            session.setAttribute(userKey, user);

            log.info("Got user data from database: " + user.getFullName());
        }

        log.info("About to set data into model: " + model);

        model.addAttribute("user", user);
    }

    public void forceReplaceUserInSession(UserDto user) {
        var session = request.getSession();
        session.removeAttribute(userKey);
        session.setAttribute(userKey, user);

        log.info("Forced replacing user in session");
    }


    /**
     * Gets simple user model (taken from session) - ID is all we need here
     * @return com.spring.start.entity.User model of currently logged user
     */
    public com.spring.start.entity.User getLoggedUser() {

        var session = request.getSession();
        var userDto = (UserDto) session.getAttribute(userKey);
        var user = com.spring.start.entity.User
                .builder()
                .id(userDto.getId())
                .build();
        return user;
    }
}
