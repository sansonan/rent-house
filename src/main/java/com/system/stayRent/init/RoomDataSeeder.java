package com.system.stayRent.init;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.github.javafaker.Faker;
import com.system.stayRent.domain.Location;
import com.system.stayRent.domain.Room;
import com.system.stayRent.domain.enumeration.GenderPreference;
import com.system.stayRent.domain.enumeration.PropertyType;
import com.system.stayRent.domain.enumeration.RoomType;
import com.system.stayRent.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.stereotype.Component;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class RoomDataSeeder implements ApplicationListener<ApplicationReadyEvent> {

    private final RoomRepository roomRepository;
    private final Faker faker = new Faker();
    private final Random random = new Random();

    @Value("${room.seed.enabled:false}")
    private boolean seedEnabled;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        if (!seedEnabled) {
            log.info("Room data seeding is disabled.");
            return;
        }

        roomRepository.count()
                .flatMap(count -> {
                    if (count >= 100_000) {
                        return Mono.empty(); // skip if already populated
                    }
                    return roomRepository.deleteAll()
                            .thenMany(generateRooms(100_000))
                            .buffer(1000) // insert in batches
                            .flatMap(roomRepository::saveAll)
                            .then();
                })
                .doOnTerminate(() -> System.out.println("Room data initialized"))
                .subscribe();
    }

    private Flux<Room> generateRooms(int total) {
        return Flux.range(1, total).map(i -> {
            String city = faker.address().city();
            String district = faker.address().streetName();
            GeoJsonPoint coordinates = new GeoJsonPoint(
                    round(randomDouble(104.8, 105.0)),
                    round(randomDouble(11.5, 11.7))
            );

            return Room.builder()
                    .id("room_" + i)
                    .name(faker.lorem().word() + " Room " + i)
                    .price(randomDouble(100, 500))
                    .floor(randomInt(1, 10))
                    .roomSize(randomDouble(12, 35))
                    .location(Location.builder()
                            .country("Cambodia")
                            .city(city)
                            .district(district)
                            .street(faker.address().streetAddress())
                            .fullAddress(faker.address().fullAddress())
                            .build())
                    .hasFan(randomBool())
                    .hasAirConditioner(randomBool())
                    .hasParking(randomBool())
                    .hasPrivateBathroom(randomBool())
                    .hasBalcony(randomBool())
                    .hasKitchen(randomBool())
                    .hasFridge(randomBool())
                    .hasWashingMachine(randomBool())
                    .hasTV(randomBool())
                    .hasWiFi(randomBool())
                    .hasElevator(randomBool())
                    .maxOccupants(randomInt(1, 5))
                    .isPetFriendly(randomBool())
                    .isSmokingAllowed(randomBool())
                    .isSharedRoom(randomBool())
                    .genderPreference(randomEnum(GenderPreference.class))
                    .roomType(randomEnum(RoomType.class))
                    .propertyType(randomEnum(PropertyType.class))
                    .distanceToCenter(randomDouble(0.5, 5))
                    .nearbyLandmarks(randomSubset(List.of("university", "mall", "market", "gym", "school")))
                    .isUtilityIncluded(randomBool())
                    .depositRequired(randomBool())
                    .minStayMonths(randomInt(1, 6))
                    .hasPhotos(true)
                    .photoCount(randomInt(3, 10))
                    .hasVideoTour(randomBool())
                    .verifiedListing(randomBool())
                    .availableFrom(LocalDateTime.now().plusDays(randomInt(1, 30)))
                    .availableTo(LocalDateTime.now().plusDays(randomInt(60, 180)))
                    .createdAt(LocalDateTime.now())
                    .lastUpdated(LocalDateTime.now())
                    .build();
        });
    }

    private boolean randomBool() {
        return random.nextBoolean();
    }

    private int randomInt(int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }

    private double randomDouble(double min, double max) {
        return min + (max - min) * random.nextDouble();
    }

    private double round(double value) {
        return Math.round(value * 100_000.0) / 100_000.0;
    }

    private <T extends Enum<?>> T randomEnum(Class<T> clazz) {
        if (clazz == null || clazz.getEnumConstants() == null || clazz.getEnumConstants().length == 0) {
            throw new IllegalArgumentException("Enum class is null or has no constants: " + clazz);
        }
        T[] constants = clazz.getEnumConstants();
        return constants[random.nextInt(constants.length)];
    }

    private List<String> randomSubset(List<String> options) {
        List<String> copy = new ArrayList<>(options);
        Collections.shuffle(copy);
        return copy.subList(0, randomInt(1, copy.size()));
    }
}