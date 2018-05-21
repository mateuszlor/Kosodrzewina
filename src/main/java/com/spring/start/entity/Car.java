package com.spring.start.entity;

import com.mysql.jdbc.StringUtils;
import com.spring.start.service.dto.CarDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

/**
 * Created by Vertig0 on 21.03.2017.
 */
@Entity
@Table(name = "car")
@NoArgsConstructor
@Getter @Setter
public class Car extends BaseEntity<Car>{

    @Builder
    public Car(long id, Boolean deleted, Date createdDate, Date modificationDate, User creationUser, User modificationUser, String brand, String model, String registrationNumber, String name, Set<Service> service, Boolean isTrailer) {
        super(id, deleted, createdDate, modificationDate, creationUser, modificationUser);
        this.brand = brand;
        this.model = model;
        this.registrationNumber = registrationNumber;
        this.name = name;
//        this.service = service;
        this.isTrailer = isTrailer;
    }

    public Car(CarDto car){
        this.brand = car.getBrand();
        this.model = car.getModel();
        this.registrationNumber = car.getRegistrationNumber();
        this.name = car.getName();
        this.isTrailer = car.getIsTrailer();
    }

    public Car(Car car) {
        super(car.getId(), car.getDeleted(), car.getCreatedDate(), car.getModificationDate(), car.getCreationUser(), car.getModificationUser());
        this.brand = car.brand;
        this.model = car.model;
        this.registrationNumber = car.registrationNumber;
        this.name = car.name;
//        this.periodicService = car.periodicService;
//        this.service = car.service;
        this.isTrailer = car.isTrailer;
    }

    @Column(name = "brand", nullable = false)
    private String brand;

    @Column(name = "model")
    private String model;

    @Column(name = "registration_number", nullable = false)
    private String registrationNumber;

    @Column(name = "name")
    private String name;

    @Column(name = "is_trailer")
    private Boolean isTrailer = Boolean.FALSE;

    public String getFullName() {
        StringBuilder sb = new StringBuilder();
        return this.getBrand() + " " + Objects.toString(this.getModel(), "") + addQuotesToNameIfNotNull();
    }

    private String addQuotesToNameIfNotNull() {
        String name = "";
        if (!StringUtils.isNullOrEmpty(this.name)) {
            name = " '" + this.name + "'";
        }

        return name;
    }

    @PrePersist
    public void defaultIsTrailer() {
        isTrailer = isTrailer == null ? false : isTrailer;
    }

}
