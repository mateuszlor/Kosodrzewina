package com.spring.start.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by Vertig0 on 14.03.2017.
 */

@Getter
@AllArgsConstructor
public enum Role {
    USER("Użytkownik"),
    ADMIN("Administrator");

    private String label;

}

