package com.spring.start.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Vertig0 on 05.06.2017.
 */
public enum ReportType {

    RENT("Wyporzyczenia"),
    SERVICE("Serwis");

    @Getter @Setter
    String label;

    ReportType(String label) {
        this.label = label;
    }
}
