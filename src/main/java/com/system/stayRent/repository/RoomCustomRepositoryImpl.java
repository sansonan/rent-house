package com.system.stayRent.repository;

import com.system.stayRent.domain.Room;
import lombok.RequiredArgsConstructor;

import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class RoomCustomRepositoryImpl implements RoomCustomRepository {
    private final ReactiveMongoTemplate mongoTemplate; // dynamic query

    @Override
    public Flux<Room> findByFilter(Query query) {
        return mongoTemplate.find(query, Room.class);
    }

    @Override
    public Mono<Long> countByFilter(Query query) {
        return mongoTemplate.count(query, Room.class);
    }


}
