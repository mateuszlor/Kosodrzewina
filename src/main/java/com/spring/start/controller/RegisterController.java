package com.spring.start.controller;

import com.spring.start.service.UserService;
import com.spring.start.service.dto.ValidationUser;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * Created by Nex0Zero on 2017-03-17.
 */
@Controller
@Log4j
public class RegisterController {

    @Autowired
    private ApplicationContext context;

    @Autowired
    @Getter @Setter
    private Environment environment;

    @Autowired
    @Getter @Setter
    private UserService userService;

    private static final String SLASH = "/";
    private static final String PAGES = "pages";
    private static final String REGISTER = "register";

    @RequestMapping(value = {SLASH + REGISTER}, method = RequestMethod.GET)
    public String registerPage(Model model) {

        log.info("Register page");
        return PAGES + SLASH + REGISTER;
    }

    @RequestMapping(value = {SLASH + REGISTER}, method = RequestMethod.POST)
    public String registerUser(@Valid @ModelAttribute("newUser") ValidationUser validationUser,
                               BindingResult bindingResult, Model model,
                               RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("error", environment.getProperty("error.form.invalidValues"));
            log.info("Wprowadzono niepoprawne wartosci do formularza dodawania nowego użytkownika");
            return "redirect:" + SLASH + REGISTER;
        }
        try {
            userService.createUser(validationUser);
            redirectAttributes.addFlashAttribute("info", environment.getProperty("message.user.success"));
            log.info("Pomyślnie dodano użytkownika: " + validationUser.getUsername());
        } catch (Exception e){
            log.error("Nie udało się utworzyć użytkownika " + validationUser.getName() + ": " + e);
        }

        return PAGES + SLASH + REGISTER;
    }


}
