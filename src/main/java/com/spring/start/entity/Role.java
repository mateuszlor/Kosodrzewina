package com.spring.start.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Vertig0 on 14.03.2017.
 */

@Entity
@Getter
@AllArgsConstructor
public class Role {

    @Id
    @Column
    public String name;

    @Column
    public String label;

    public static Role USER = new Role("USER", "Użytkownik");
    public static Role ADMIN = new Role("ADMIN", "Administrator");
}

