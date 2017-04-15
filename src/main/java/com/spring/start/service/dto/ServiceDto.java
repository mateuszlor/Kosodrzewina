package com.spring.start.service.dto;

import lombok.*;
import org.joda.time.DateTime;
import org.joda.time.Interval;

import java.math.BigDecimal;

/**
 * Created by Vertig0 on 30.03.2017.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServiceDto {

    private long id;

    private long car;

    private String type;

    private long name;

    private String date;

    private String dateTo;

    private boolean isPeriodic;

    private BigDecimal cost;

    public Integer getRemainingDays(){

        if(isPeriodic) {
            return new Interval(new DateTime(), new DateTime(dateTo)).toPeriod().getDays() + 1;
        }
        return null;
    }
}
