package com.system.stayRent.mapper;

import com.system.stayRent.dto.RoomFilterDTO;

import java.util.Map;

public class RoomFilterDTOMapper {
    public static RoomFilterDTO toRoomFilterDTO(Map<String, String> params) {
        RoomFilterDTO dto = new RoomFilterDTO();
        if (params.containsKey("name")) {
            dto.setName(params.get("name"));
        }
        if(params.containsKey("floor")) {
            dto.setFloor(Integer.parseInt(params.get("floor")));

        }
        return dto;
    }
}
