package com.system.stayRent.controller;

import com.system.stayRent.dto.PageDTO;
import com.system.stayRent.dto.RoomDTO;
import com.system.stayRent.dto.RoomFilterDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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

//    @GetMapping("/filter")
//    public Flux<RoomDTO> filter(@ModelAttribute RoomFilterDTO roomFilterDTO) {
//        return roomService.getRoomByFilter(roomFilterDTO);
//    }
//    @GetMapping("/filter")
//    public Flux<RoomDTO> filter(RoomFilterDTO roomFilterDTO) {
//        return roomService.getRoomByFilter(roomFilterDTO);
//    }
    @GetMapping("/search/pagination")
    public Mono<PageDTO<RoomDTO>> getRoomByFilterPagination(RoomFilterDTO roomFilterDTO) {
        return roomService.getRoomByFilterPagination(roomFilterDTO);
    }

    //version with header
    @GetMapping("search/pg")
    public Mono<ResponseEntity<PageDTO<RoomDTO>>> getRoomByFilterPaginationWithHeader(@Valid RoomFilterDTO roomFilterDTO) {
        return  roomService.getRoomByFilterPagination(roomFilterDTO)
                .map(page -> ResponseEntity.ok()
                        .header("X-Total-Count", String.valueOf(page.getTotalElements()))
                        .body(page)
                );

    }
}
