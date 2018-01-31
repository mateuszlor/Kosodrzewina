package com.spring.start.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by Vertig0 on 27.03.2017.
 */
@NoArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@AllArgsConstructor
@Getter
public enum DictionaryType {

    SERVICE("Serwis"),
    PAYMENT("Okresowe");

    private String displayName;

}
