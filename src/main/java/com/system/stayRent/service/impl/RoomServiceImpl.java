package com.system.stayRent.service.impl;

import com.system.stayRent.domain.Room;
import com.system.stayRent.dto.PageDTO;
import com.system.stayRent.dto.RoomDTO;
import com.system.stayRent.dto.RoomFilterDTO;
import com.system.stayRent.exception.RoomNotFoundException;
import com.system.stayRent.mapper.RoomMapper;
import com.system.stayRent.repository.RoomCustomRepository;
import com.system.stayRent.util.RoomCriteriaBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import com.system.stayRent.repository.RoomRepository;
import com.system.stayRent.service.RoomService;

import java.util.List;


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
    public Mono<PageDTO<RoomDTO>> getRoomByFilterPagination(RoomFilterDTO filterDTO) {
        // Base query (filters only)
        Criteria criteria = RoomCriteriaBuilder.build(filterDTO);
        // Count total elements (NO pagination)
        Mono<Long> countMono = roomCustomRepository.countByFilter(new Query(criteria));

        //  Content query (with pagination)
        Query query = new Query(criteria)
                .skip((long) filterDTO.getPage() * filterDTO.getSize())
                .limit(filterDTO.getSize());

        query.with(RoomCriteriaBuilder.sort(filterDTO));

        // Fetch page content
        Flux<RoomDTO> contentFlux =
                roomCustomRepository.findByFilter(query)
                        .map(roomMapper::toRoomDTO);

        // Build page response
        return Mono.zip(countMono, contentFlux.collectList())
                .map(tuple -> {
                    long total = tuple.getT1();
                    List<RoomDTO> content = tuple.getT2();

                    int totalPages = (int) Math.ceil(
                            (double) total / filterDTO.getSize()
                    );

                    return new PageDTO<>(
                            filterDTO.getPage(),
                            filterDTO.getSize(),
                            total,
                            totalPages,
                            content
                    );
                });

    }




}
