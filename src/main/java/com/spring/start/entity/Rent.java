package com.spring.start.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Vertig0 on 09.04.2017.
 */
@Entity
@Table(name = "rent")
@NoArgsConstructor
public class Rent extends BaseEntity<Rent>{

    @ManyToOne
    @JoinColumn(name = "customer", nullable = false)
    @Getter
    @Setter
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "car", nullable = false)
    @Getter
    @Setter
    private Car car;

    @Column(name = "start_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Getter @Setter
    private Date startDate;

    @Column(name = "end_date")
    @Temporal(TemporalType.TIMESTAMP)
    @Getter @Setter
    private Date endDate;

    @Column(name = "income", nullable = false)
    @Getter
    @Setter
    private BigDecimal income;

    @Column(name = "start_course")
    @Getter
    @Setter
    private Long startCourse;

    @Column(name = "end_course")
    @Getter
    @Setter
    private Long endCourse;

    @Column(name = "description")
    @Getter
    @Setter
    private String description;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    @Getter
    @Setter
    private RentStatus status;

    @ManyToOne
    @JoinColumn(name = "trailer")
    @Getter
    @Setter
    private Rent trailer;

    @Builder
    public Rent(long id, Boolean deleted, Date createdDate, Date modificationDate, User creationUser, User modificationUser, Customer customer, Car car, Date startDate, Date endDate, BigDecimal income, Long startCourse, Long endCourse, String description, RentStatus status, Rent trailer) {
        super(id, deleted, createdDate, modificationDate, creationUser, modificationUser);
        this.customer = customer;
        this.car = car;
        this.startDate = startDate;
        this.endDate = endDate;
        this.income = income;
        this.startCourse = startCourse;
        this.endCourse = endCourse;
        this.description = description;
        this.status = status;
        this.trailer = trailer;
    }
}
