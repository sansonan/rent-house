package com.system.stayRent.service;

import com.system.stayRent.dto.RoomDTO;
import reactor.core.publisher.Mono;

public interface RoomService {
    Mono<RoomDTO > createRoom(RoomDTO roomDTO);
    Mono<RoomDTO> getRoomById(String id);


}
