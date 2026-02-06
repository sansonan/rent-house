package com.system.stayRent.service.impl;

import com.system.stayRent.domain.Room;
import com.system.stayRent.dto.RoomDTO;
import com.system.stayRent.dto.RoomFilterDTO;
import com.system.stayRent.exception.RoomNotFoundException;
import com.system.stayRent.mapper.RoomMapper;
import com.system.stayRent.repository.RoomCustomRepository;
import com.system.stayRent.util.RoomCriteriaBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import com.system.stayRent.repository.RoomRepository;
import com.system.stayRent.service.RoomService;

@Service
@Slf4j
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;
    private final RoomCustomRepository roomCustomRepository;


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
                .switchIfEmpty(Mono.error(new RoomNotFoundException(id)))
                .doOnNext(room -> log.info("Room found: {}", room))
                .map(roomMapper::toRoomDTO);
    }

    @Override
    public Mono<RoomDTO> updateRoom(String id, RoomDTO roomDTO) {
        log.debug("Updating room by id:  {} with data : {}", id, roomDTO);
        return roomRepository.findById(id)
                .switchIfEmpty(Mono.error(new RoomNotFoundException(id)))
                .flatMap(existingRoom -> {
                    existingRoom.setName(roomDTO.getName());
                    existingRoom.setAttributes(roomDTO.getAttributes());
                    return roomRepository.save(existingRoom);
                })
                .map(roomMapper::toRoomDTO);
        //Target: update existing object room
        /*
        get from db
        update new value
        save into db
         */
    }


    // Add function add Mapper
    @Override
    public Mono<RoomDTO> updateRoomMapper(String id, RoomDTO roomDTO) {
        log.debug("Updating room mapper : {}", id);
        return roomRepository.findById(id)
                .switchIfEmpty(Mono.error(new RoomNotFoundException(id)))
                .flatMap(existing -> {
                    roomMapper.updateRoomFromDTO(roomDTO, existing);
                    return roomRepository.save(existing);
                })
                .map(roomMapper::toRoomDTO);

    }

    @Override
    public Mono<Void> deleteRoom(String id) {
        return roomRepository.deleteById(id)
                .switchIfEmpty(Mono.error(new RoomNotFoundException(id)))
                .doOnSuccess(deleted -> log.info("Room deleted: {}", deleted));
    }

    @Override
    public Flux<RoomDTO> getAllRooms() {
        return roomRepository.findAll().map(roomMapper::toRoomDTO);
    }

    @Override
    public Flux<RoomDTO> searchRoomsByName(String name) {
        return roomRepository.findByName(name).map(roomMapper::toRoomDTO);
    }

    @Override
    public Flux<RoomDTO> getRoomByFilter(RoomFilterDTO filterDTO) {
        Query query = RoomCriteriaBuilder.build(filterDTO);
        return  roomCustomRepository.findByFilter(query).map(roomMapper::toRoomDTO);
    }
}
