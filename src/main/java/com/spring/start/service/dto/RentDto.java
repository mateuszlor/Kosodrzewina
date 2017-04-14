package com.spring.start.service.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

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
    }

    private long id;

    private long customer;

    private long car;

    private String startDate;

    private String endDate;

    private BigDecimal income;

    private Long startCourse;

    private Long endCourse;

    private String description;

    private Boolean isTrailer = Boolean.FALSE;

    private BigDecimal additionalIncome;

    private long trailer;


}
