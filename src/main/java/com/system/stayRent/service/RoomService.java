package com.system.stayRent.service;

import com.system.stayRent.dto.RoomDTO;
import com.system.stayRent.dto.RoomFilterDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RoomService {
    Mono<RoomDTO > createRoom(RoomDTO roomDTO);
    Mono<RoomDTO> getRoomById(String id);
    Mono<RoomDTO> updateRoom(String id, RoomDTO roomDTO);
    Mono<RoomDTO> updateRoomMapper(String id, RoomDTO roomDTO);
    Mono<Void> deleteRoom(String id);
    Flux<RoomDTO> getAllRooms();
    // case study
    Flux<RoomDTO> searchRoomsByName(String name);
    Flux<RoomDTO> getRoomByFilter(RoomFilterDTO filterDTO);




}
