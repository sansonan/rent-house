//package com.system.stayRent.roomservice.impl;
//
//
//import com.system.stayRent.domain.Room;
//import com.system.stayRent.dto.RoomDTO;
//import com.system.stayRent.mapper.RoomMapper;
//import com.system.stayRent.repository.RoomCustomRepository;
//import com.system.stayRent.repository.RoomRepository;
//import com.system.stayRent.service.impl.RoomServiceImpl;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import reactor.core.publisher.Mono;
//import reactor.test.StepVerifier;
//
//import static org.mockito.Mockito.when;
//
////Test by reactive style
//@ExtendWith(MockitoExtension.class)
//public class RoomServiceImplTest {
//    @Mock
//    private RoomRepository roomRepository;
//
//    @Mock
//    private RoomMapper roomMapper;
//
//    @Mock
//    private RoomCustomRepository roomCustomRepository;
//
//    @InjectMocks
//    private RoomServiceImpl roomService;
//
////    @Test
//    void createRoom_success() {
//
//        //Given
//        RoomDTO roomDTO = new RoomDTO();
//        roomDTO.setName("Room");
//        Room room = new Room();
//        room.setName("Room");
//
//        //when
//        when(roomMapper.toRoom(roomDTO)).thenReturn(room);
//        when(roomRepository.save(room)).thenReturn(Mono.just(room));
//        when(roomMapper.toRoomDTO(room)).thenReturn(roomDTO);
//
//        //then
//        StepVerifier.create(roomService.createRoom(roomDTO))
//        .expectNext(roomDTO)
//        .verifyComplete();
//
//    }
//}
