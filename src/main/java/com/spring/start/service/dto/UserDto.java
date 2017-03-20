package com.spring.start.service.dto;

import com.spring.start.entity.Role;
import lombok.*;
import lombok.experimental.var;
import lombok.extern.log4j.Log4j;

/**
 * Created by Mateusz on 19.03.2017.
 */
@Log4j
@var
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private long id;
    private String username;
    private String name;
    private String surname;
    private String password;
    private String oldPassword;
    private String newPassword;
    private String newPassword2;
    private Role role;
    private boolean enabled;

    public String getFullName(){
        return name + " " + surname;
    }
}
