package com.system.stayRent.repository;

import com.system.stayRent.domain.Room;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface RoomRepository extends ReactiveCrudRepository<Room, String> {

   Flux<Room> findByNameContainingIgnoreCase(String name);
}
