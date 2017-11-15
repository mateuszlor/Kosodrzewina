package com.spring.start.entity;

import com.spring.start.service.dto.CarDto;
import lombok.*;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Vertig0 on 21.03.2017.
 */
@Entity
@Table(name = "car")
@NoArgsConstructor
@Getter @Setter
public class Car extends BaseEntity<Car>{

    @Builder
    public Car(long id, Boolean deleted, Date createdDate, Date modificationDate, User creationUser, User modificationUser, String brand, String model, String registrationNumber, String name, Set<PeriodicService> periodicService, Set<Service> service, Boolean isTrailer) {
        super(id, deleted, createdDate, modificationDate, creationUser, modificationUser);
        this.brand = brand;
        this.model = model;
        this.registrationNumber = registrationNumber;
        this.name = name;
//        this.periodicService = periodicService;
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

//    @SuppressWarnings("unchecked")
//    public static Car mergeUpdate(Car basic, Car edited) throws IllegalAccessException, InstantiationException {
//        Class<?> clazz = basic.getClass();
//        List<Field> fields = Arrays.asList(clazz.getDeclaredFields());
//        List<Field> subFields = Arrays.asList(clazz.getSuperclass().getDeclaredFields());
//        List<Field> finalFields = Stream.concat(fields.stream(), subFields.stream()).collect(Collectors.toList());
//        Object returnValue = clazz.newInstance();
//        for (Field field : finalFields) {
//            field.setAccessible(true);
//            Object value1 = field.get(basic);
//            Object value2 = field.get(edited);
//            Object value = (value1 != null && value2 != null && !value1.equals(value2) && !field.getName().equals("id")) ? value2 : value1;
//            field.set(returnValue, value);
//        }
//        return (Car) returnValue;
//    }

    @Column(name = "brand", nullable = false)
    private String brand;

    @Column(name = "model")
    private String model;

    @Column(name = "registration_number", nullable = false)
    private String registrationNumber;

    @Column(name = "name")
    private String name;

//    @OneToMany(mappedBy = "car", fetch = FetchType.LAZY)
//    Collection<PeriodicService> periodicService = new ArrayList<>();

//    @OneToMany(mappedBy = "car", fetch = FetchType.LAZY)
//    Collection<Service> service = new ArrayList<>();

    @Column(name = "is_trailer")
    private Boolean isTrailer = Boolean.FALSE;

    public String getFullName() {
        return this.getBrand() + " " + this.getModel() + "'" + this.getName() + "'";
    }

    @PrePersist
    public void defaultIsTrailer() {
        isTrailer = isTrailer == null ? false : isTrailer;
    }

}
