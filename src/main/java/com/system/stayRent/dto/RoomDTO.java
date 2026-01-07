package com.system.stayRent.dto;

import lombok.Data;

import java.util.Map;

@Data
public class RoomDTO {
    private String name;
    private Map<String, Object> attributes;
}
