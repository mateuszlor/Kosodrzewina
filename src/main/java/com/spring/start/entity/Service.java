package com.spring.start.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Vertig0 on 29.03.2017.
 */
@Entity
@Table(name = "service")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Service {

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

    @Column(name = "execute", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date execute;

    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    private User createdBy;

    private BigDecimal cost;
}
