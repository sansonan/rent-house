package com.system.stayRent.repository;

import com.system.stayRent.domain.Room;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface RoomRepository extends ReactiveCrudRepository<Room, String> {

   Flux<Room> findByNameContainingIgnoreCase(String name);

   //customize query
   @Query("{'name':  ?0}")
   Flux<Room> findByName(String name);
}
