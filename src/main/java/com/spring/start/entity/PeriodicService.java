package com.spring.start.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Vertig0 on 29.03.2017.
 */
@Entity
@Table(name = "periodic_service")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class PeriodicService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private long id;

    @ManyToOne
    @JoinColumn(name = "car", nullable = false)
    private Car car;

    @ManyToOne
    @JoinColumn(name = "type", nullable = false)
    private Dictionary type;

    @Column(name = "date_from", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateFrom;

    @Column(name = "date_to", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateTo;

    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    private User createdBy;

    private BigDecimal cost;
}
