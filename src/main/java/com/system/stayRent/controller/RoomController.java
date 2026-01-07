package com.system.stayRent.controller;

import com.system.stayRent.dto.RoomDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import com.system.stayRent.service.RoomService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/rooms")
public class RoomController {
    private final RoomService roomService;

    @PostMapping
    public Mono<RoomDTO> createRoom(@RequestBody RoomDTO roomDTO) {
            return roomService.createRoom(roomDTO);

    }
    @RequestMapping("/{roomId}")
    public Mono<RoomDTO> getRoomById(@PathVariable String roomId) {
        return roomService.getRoomById(roomId);
    }
}
