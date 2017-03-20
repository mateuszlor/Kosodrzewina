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

    private static UserService userService;

    @Autowired
    private UserService userService_auto;

    private static HttpServletRequest request;

    @Autowired
    private HttpServletRequest request_auto;

    @PostConstruct
    public void init() {
        var msg = String.format("Initializing ControllerHelper: userService = %s, request = %s", userService_auto, request_auto);
        log.info(msg);
        ControllerHelper.userService = userService_auto;
        ControllerHelper.request = request_auto;
    }

    public static void setUserData(Model model) {

        var msg = String.format("About to set user data: request = %s, userService = %s, securityContext = %s", request, userService, SecurityContextHolder.getContext().getAuthentication());
        log.info(msg);

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

    public static void forceReplaceUserInSession(UserDto user){
        var userKey = "user";
        var session = request.getSession();
        session.removeAttribute(userKey);
        session.setAttribute(userKey, user);

        log.info("Forced replacing user in session");
    }
}
