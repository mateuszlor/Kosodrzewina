package com.spring.start.helper;

import com.spring.start.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

@Component
public class SpringSecurityAuditorAware implements AuditorAware<User>{

    @Autowired
    private ControllerHelper helper;

    @Override
    public User getCurrentAuditor() {
        return helper.getLoggedUser();
    }

}
