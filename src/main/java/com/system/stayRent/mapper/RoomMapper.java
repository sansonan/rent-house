package com.system.stayRent.mapper;

import com.system.stayRent.domain.Room;
import com.system.stayRent.dto.RoomDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;


@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface RoomMapper {

    // Request → Entity (ignore id)

    Room toRoom(RoomDTO roomDTO);
    RoomDTO toRoomDTO(Room room);

    @Mapping(target = "id", ignore = true)
    void updateRoomFromDTO(RoomDTO dto, @MappingTarget Room entity);


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
