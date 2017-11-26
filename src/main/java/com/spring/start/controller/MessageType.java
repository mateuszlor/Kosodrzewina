package com.spring.start.controller;

public enum MessageType {

    ERROR("error"),
    SUCCESS("success");

    private final String name;

    MessageType(String s) {
        name = s;
    }
    public String toString() {
        return this.name;
    }

}
