package com.system.stayRent.constant;

public enum RoomSortableField {

    NAME(RoomField.NAME.value()),
    PRICE(RoomField.PRICE.value()),
    FLOOR(RoomField.FLOOR.value());

    private final String field;

    RoomSortableField(String field) {
        this.field = field;
    }

    public String field() {
        return field;
    }

    public static String safeValue(String value) {
        for (RoomSortableField f : values()) {
            if (f.name().equalsIgnoreCase(value)) {
                return f.field();
            }
        }
        return RoomField.NAME.value(); // default
    }
}
