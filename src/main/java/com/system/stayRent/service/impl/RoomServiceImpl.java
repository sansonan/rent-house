package com.system.stayRent.service.impl;

import com.system.stayRent.domain.Room;
import com.system.stayRent.dto.RoomDTO;
import com.system.stayRent.mapper.RoomMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import com.system.stayRent.repository.RoomRepository;
import com.system.stayRent.service.RoomService;

@Service
@Slf4j
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;


    @Override
    public Mono<RoomDTO> createRoom(RoomDTO roomDTO) {
        log.debug("Saving room to DB: {}", roomDTO);
        Room room = roomMapper.toRoom(roomDTO);
        return roomRepository.save(room)
                .doOnSuccess(saved -> log.info("Room saved {} ", saved))
                .map(roomMapper::toRoomDTO);
    }

    @Override
    public Mono<RoomDTO> getRoomById(String id) {
        log.debug("Getting room by id {}", id);
        return roomRepository.findById(id)
                .doOnNext(room -> log.info("Room found: {}", room))
                .map(roomMapper::toRoomDTO);
    }

}
