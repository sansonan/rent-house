package com.system.stayRent.repository;

import com.system.stayRent.domain.Room;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface RoomRepository extends ReactiveCrudRepository<Room, String> {
}
