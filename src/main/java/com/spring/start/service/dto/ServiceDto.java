package com.spring.start.service.dto;

import lombok.*;
import org.joda.time.DateTime;
import org.joda.time.Interval;

import javax.validation.constraints.NotNull;
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

    @NotNull
    private long car;

    @NotNull
    private String type;

    @NotNull
    private long name;

    @NotNull
    private String date;

    private String dateTo;

    private boolean isPeriodic;

    private BigDecimal cost;

    public Long getRemainingDays(){

        if(isPeriodic) {
            return new Interval(new DateTime(), new DateTime(dateTo)).toDuration().getStandardDays() + 1;
        }
        return null;
    }
}
