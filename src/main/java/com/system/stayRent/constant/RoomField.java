package com.system.stayRent.constant;

import lombok.Data;


public enum RoomField {
    NAME("name"),
    PRICE("attributes.price"),
    FLOOR("attributes.floor");

    private final String value;

    RoomField(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
