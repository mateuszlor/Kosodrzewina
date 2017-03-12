package com.spring.start.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Vertig0 on 12.03.2017.
 */
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false, unique = true)
    @Getter @Setter
    private long id;

    @Column(name = "name", nullable = false)
    @Getter @Setter
    private String name;

    @Column(name = "surname", nullable = false)
    @Getter @Setter
    private String surname;

    @Column(name = "login", nullable = false)
    @Getter @Setter
    private String login;

    @Column(name = "password", nullable = false)
    @Getter @Setter
    private String password;

}
