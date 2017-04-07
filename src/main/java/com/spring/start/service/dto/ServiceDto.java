package com.spring.start.service.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Created by Vertig0 on 30.03.2017.
 */
@Getter
@Setter
@Builder
public class ServiceDto {

    private long id;

    private long car;

    private String type;

    private long name;

    private String date;

    private String dateTo;

    private boolean isPeriodic;

}
