package com.system.stayRent.mapper;

import com.system.stayRent.domain.Room;
import com.system.stayRent.dto.RoomDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface RoomMapper {

    // Request → Entity (ignore id)
    @Mapping(target = "id", ignore = true)
    Room toRoom(RoomDTO roomDTO);
    RoomDTO toRoomDTO(Room room);

//    public  RoomDTO toRoomDto (Room room) {
//        RoomDTO dto = new RoomDTO();
//        dto.setName(room.getName());
//        dto.setAttributes(room.getAttributes());
//        return dto;
//
//    }
//
//    public Room toRoom (RoomDTO dto) {
//        Room room = new Room();
//        room.setName(dto.getName());
//        room.setAttributes(dto.getAttributes());
//        return room;
//    }
}
