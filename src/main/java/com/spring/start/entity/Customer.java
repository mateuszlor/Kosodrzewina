package com.spring.start.entity;

import com.spring.start.service.dto.CustomerDto;
import lombok.*;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Vertig0 on 18.03.2017.
 */
@Entity
@Table(name = "customer")
@NoArgsConstructor
@Getter @Setter
public class Customer extends BaseEntity<Customer>{

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

    @Builder
    public Customer(long id, Boolean deleted, Date createdDate, Date modificationDate, User creationUser, User modificationUser, String name, String surname, String username, String address, String phone) {
        super(id, deleted, createdDate, modificationDate, creationUser, modificationUser);
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.address = address;
        this.phone = phone;
    }

    public Customer(CustomerDto dto) {
        this.name = dto.getName();
        this.surname = dto.getSurname();
        this.username = dto.getUsername();
        this.address = dto.getAddress();
        this.phone = dto.getPhone();
    }

    public String getFullName() {
        return this.getName() + " " + this.getSurname();
    }

}
