package com.system.stayRent.service;

import com.system.stayRent.dto.PageDTO;
import com.system.stayRent.dto.RoomDTO;
import com.system.stayRent.dto.RoomFilterDTO;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RoomService {
    Mono<RoomDTO > createRoom(RoomDTO roomDTO);
    Mono<RoomDTO> getRoomById(String id);
    Mono<RoomDTO> updateRoomMapper(String id, RoomDTO roomDTO);
    Mono<Void> deleteRoom(String id);
    Flux<RoomDTO> getAllRooms();
    Flux<RoomDTO> getRoomByFilter(RoomFilterDTO filterDTO);
    Mono<PageDTO<RoomDTO>> getRoomByFilterPagination(RoomFilterDTO filterDTO);




}
