package com.spring.start.entity;

import com.spring.start.operations.Functions;
import com.spring.start.service.dto.ServiceDto;
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
@Getter
@Setter
public class Service extends BaseEntity<Service>{

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id", nullable = false, unique = true)
//    private long id;

    @ManyToOne
    @JoinColumn(name = "car", nullable = false)
    private Car car;

    @ManyToOne
    @JoinColumn(name = "type", nullable = false)
    private Dictionary type;

    /**
     *  Data wykonania/ rozpoczęcia
     * */
    @Column(name = "execution_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date executionDate;

    /**
     *  Data zakończenia
     * */
    @Column(name = "end_date")
    @Temporal(TemporalType.DATE)
    private Date endDate;

    private BigDecimal cost;

    @Builder
    public Service(long id, Boolean deleted, Date createdDate, Date modificationDate, User creationUser, User modificationUser, Car car, Dictionary type, Date executionDate, Date endDate, BigDecimal cost) {
        super(id, deleted, createdDate, modificationDate, creationUser, modificationUser);
        this.car = car;
        this.type = type;
        this.executionDate = executionDate;
        this.endDate = endDate;
        this.cost = cost;
    }

    public Service(ServiceDto serviceDto) {
        this.executionDate = Functions.convertStringToDate(serviceDto.getDate());
        this.endDate = Functions.convertStringToDate(serviceDto.getDateTo());
        this.cost = serviceDto.getCost();
    }
}
