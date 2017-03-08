package com.spring.start.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Vertig0 on 08.03.2017.
 */
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false, unique = true)
    @Getter @Setter
    private long id;

    @Column(name = "name")
    @NotNull
    @Getter @Setter
    private String name;

    @Column(name = "surname")
    @NotNull
    @Getter @Setter
    private String surname;

    @Column(name = "login")
    @NotNull
    @Getter @Setter
    private String login;

    @Column(name = "password")
    @NotNull
    @Getter @Setter
    private String password;

}
