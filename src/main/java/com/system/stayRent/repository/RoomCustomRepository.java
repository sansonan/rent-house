package com.system.stayRent.repository;

import com.system.stayRent.domain.Room;
import org.springframework.data.mongodb.core.query.Query;
import reactor.core.publisher.Flux;

public interface RoomCustomRepository {
    Flux<Room> findByFilter(Query query);
}
