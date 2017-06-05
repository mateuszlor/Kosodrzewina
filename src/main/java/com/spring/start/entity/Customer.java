package com.spring.start.entity;

import lombok.*;

import javax.persistence.*;

/**
 * Created by Vertig0 on 18.03.2017.
 */
@Entity
@Table(name = "customer")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    @Getter @Setter
    private long id;

    @Column(name = "name", nullable = false)
    @Getter @Setter
    private String name;

    @Column(name = "surname", nullable = false)
    @Getter @Setter
    private String surname;

    @Column(name = "username")
    @Getter @Setter
    private String username;

    @Column(name = "address")
    @Getter @Setter
    private String address;

    @Column(name = "phone")
    @Getter @Setter
    private String phone;

    public String getFullName() {
        return this.getName() + " " + this.getSurname();
    }

}
