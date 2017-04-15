package com.spring.start.entity;

import lombok.*;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Vertig0 on 09.04.2017.
 */
@Entity
@Table(name = "rent")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Rent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    @Getter
    @Setter
    private long id;

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

    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    @Getter
    @Setter
    private User createdBy;

    @Column(name = "active", nullable = false)
    @Getter
    @Setter
    private int active;

    @ManyToOne
    @JoinColumn(name = "trailer")
    @Getter
    @Setter
    private Rent trailer;

}
