package com.spring.start.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Created by Vertig0 on 30.03.2017.
 */
public class ServiceDto {

    @Getter
    @Setter
    private long id;

    @Getter @Setter
    private long car;

    @Getter @Setter
    private String type;

    @Getter @Setter
    private long name;

    @Getter @Setter
    private String date;

    @Getter @Setter
    private String dateTo;


}
