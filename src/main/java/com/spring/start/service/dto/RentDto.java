package com.spring.start.service.dto;

import com.spring.start.entity.Money;
import com.spring.start.entity.RentStatus;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Created by Vertig0 on 09.04.2017.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RentDto {

    public RentDto(RentDto rent) {
        this.customer = rent.customer;
        this.car = rent.car;
        this.startDate = rent.startDate;
        this.endDate = rent.endDate;
        this.income = rent.income;
        this.startCourse = rent.startCourse;
        this.endCourse = rent.endCourse;
        this.description = rent.description;
        this.isTrailer = rent.isTrailer;
        this.additionalIncome = rent.additionalIncome;
        this.trailer = rent.trailer;
        this.status = rent.status;
    }

    private long id;

    @NotNull
    private long customer;

    @NotNull
    private long car;

    @NotNull
    private String startDate;

    private String endDate;

    private Money income;

    private Long startCourse;

    private Long endCourse;

    private String description;

    private Boolean isTrailer = Boolean.FALSE;

    private Money additionalIncome;

    private long trailer;

    private RentStatus status;

}
