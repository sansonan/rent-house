package com.system.stayRent.controller;

import com.system.stayRent.dto.RoomDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import com.system.stayRent.service.RoomService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/rooms")
public class RoomController {
    private final RoomService roomService;

    @PostMapping
    @Operation(summary = "Create Room")
    public Mono<RoomDTO> createRoom(@Valid @RequestBody RoomDTO roomDTO) {
            return roomService.createRoom(roomDTO);
    }
    @GetMapping("/{roomId}")
    @Operation(summary = "Get room by ID", parameters = @Parameter(in = ParameterIn.PATH, name = "roomId"))
    public Mono<RoomDTO> getRoomById(@PathVariable String roomId) {
        return roomService.getRoomById(roomId);
    }

    @PutMapping("/{roomId}")
    @Operation(summary = "Update Room")
    public Mono<RoomDTO> updateRoom(@PathVariable String roomId, @RequestBody RoomDTO roomDTO) {
        return roomService.updateRoomMapper(roomId, roomDTO);
    }

    @DeleteMapping("/{roomId}")
    @Operation(summary = "Deleted Room by ID")
    public Mono<Void> deleteRoom(@PathVariable String roomId) {
        return roomService.deleteRoom(roomId);
    }

    @GetMapping("/find/all")
    @Operation(summary = "Find all room")
    public Flux<RoomDTO> findAll() {
        return roomService.getAllRooms();
    }


    //For case study
    @GetMapping("/search")
    public Flux<RoomDTO> search(@RequestParam(value = "name", required = false) String name) {
        return roomService.searchRoomsByName(name == null ? "" : name);
    }
}
