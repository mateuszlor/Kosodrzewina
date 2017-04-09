package com.spring.start.service.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Vertig0 on 09.04.2017.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RentDto {

    @Getter
    @Setter
    private long id;

    @Getter
    @Setter
    private long customer;

    @Getter
    @Setter
    private long car;

    @Getter @Setter
    private String startDate;

    @Getter @Setter
    private String endDate;

    @Getter
    @Setter
    private BigDecimal income;

    @Getter
    @Setter
    private Long startCourse;

    @Getter
    @Setter
    private Long endCourse;

    @Getter
    @Setter
    private String description;



}
