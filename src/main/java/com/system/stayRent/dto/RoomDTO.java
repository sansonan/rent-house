package com.system.stayRent.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NonNull;
import lombok.Singular;

import java.util.Map;

@Data
public class RoomDTO {

    @NotBlank(message = "Room name is Require")
    @Size(max = 100, message = "Room name")
    private String name;
    private Map<String, Object> attributes;
}
