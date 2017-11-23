package com.spring.start.entity;

import lombok.NoArgsConstructor;

/**
 * Created by Vertig0 on 27.03.2017.
 */
@NoArgsConstructor
public enum DictionaryType {

    SERVICE("Serwis"),
    PAYMENT("Okresowe");

    private String displayName;

    DictionaryType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

}
