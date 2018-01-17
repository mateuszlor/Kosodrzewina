package com.spring.start.controller.admin;

import com.spring.start.controller.BaseController;
import com.spring.start.repository.UserRepository;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j
public class UserAdministrationController extends BaseController{

    private static final String USER_MANAGEMENT_PAGE = "user-management";

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = SLASH + ADMIN + SLASH + USER_MANAGEMENT_PAGE)
    public String showUserManagementPage(Model model) {

        model.addAttribute("users", userRepository.findAllByEnabledTrue());
        log.info("Strona zarządzania użytkownikami");
        return PAGES + SLASH + ADMIN + SLASH + USER_MANAGEMENT_PAGE;
    }


}
