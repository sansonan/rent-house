package com.system.stayRent.constant;

import java.util.Arrays;
import java.util.List;

public class RoomConstants {
    private RoomConstants() {}

    public static final String ATT = "attributes.";
    public static final String FIELD_NAME = "name";
    public static final String FIELD_PRICE = ATT+"price";
    public static final String FIELD_FLOOR = ATT+"floor";

    // price operators
    public static final String OP_LT = "lt";
    public static final String OP_GT = "gt";
    public static final String OP_LTE = "lte";
    public static final String OP_GTE = "get";
    public static final String OP_EQ = "eq";

    //sort allow - list
    public static final List<String> ALLOWED_SORT_FIELDS = List.of("name","price","floor");

}
