package com.spring.start.entity;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

/**
 * Created by Vertig0 on 29.03.2017.
 */
@Entity
@Table(name = "periodic_service")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PeriodicService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    @Getter
    @Setter
    private long id;

    @ManyToOne
    @JoinColumn(name = "car", nullable = false)
    @Getter
    @Setter
    private Car car;

    @ManyToOne
    @JoinColumn(name = "type", nullable = false)
    @Getter
    @Setter
    private Dictionary type;

    @Column(name = "date_from", nullable = false)
    @Temporal(TemporalType.DATE)
    @Getter @Setter
    private Date dateFrom;

    @Column(name = "date_to", nullable = false)
    @Temporal(TemporalType.DATE)
    @Getter @Setter
    private Date dateTo;

    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    @Getter
    @Setter
    private User createdBy;

}
