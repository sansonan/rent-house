package com.system.stayRent.repository;

import com.system.stayRent.domain.Room;
import org.springframework.data.mongodb.core.query.Query;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RoomCustomRepository {
    Flux<Room> findByFilter(Query query);
    Mono<Long> countByFilter(Query query);
}
