package com.spring.start.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Vertig0 on 29.03.2017.
 */
@Entity
@Table(name = "service")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Service {

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

    @Column(name = "execute", nullable = false)
    @Temporal(TemporalType.DATE)
    @Getter
    @Setter
    private Date execute;

    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    @Getter
    @Setter
    private User createdBy;

}
