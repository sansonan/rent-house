package com.system.stayRent.util;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.system.stayRent.dto.RoomFilterDTO;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;


import lombok.extern.slf4j.Slf4j;

import static com.system.stayRent.constant.RoomConstants.*;

@Slf4j
public class RoomCriteriaBuilder {

    private RoomCriteriaBuilder(){}

    public static Criteria build(RoomFilterDTO filter) {

        List<Criteria> criterias = new ArrayList<>();

        // --- Address criteria ---
        if (Objects.nonNull(filter.getProvinceCode()) && !filter.getProvinceCode().isBlank()) {
            criterias.add(Criteria.where("address.provinceCode").is(filter.getProvinceCode()));
        }
        if (Objects.nonNull(filter.getDistrictCode()) && !filter.getDistrictCode().isBlank()) {
            criterias.add(Criteria.where("address.districtCode").is(filter.getDistrictCode()));
        }
        if (Objects.nonNull(filter.getCommuneCode()) && !filter.getCommuneCode().isBlank()) {
            criterias.add(Criteria.where("address.communeCode").is(filter.getCommuneCode()));
        }
        if (Objects.nonNull(filter.getVillageCode()) && !filter.getVillageCode().isBlank()) {
            criterias.add(Criteria.where("address.villageCode").is(filter.getVillageCode()));
        }


        if (Objects.nonNull(filter.getName())) {
            criterias.add(Criteria.where(FIELD_NAME).is(filter.getName()));
        }

        if (Objects.nonNull(filter.getFloor())) {
            criterias.add(Criteria.where(FIELD_FLOOR).is(filter.getFloor()));
        }

        if (Objects.nonNull(filter.getRoomSizeMin()) || Objects.nonNull(filter.getRoomSizeMax())) {
            Criteria c = Criteria.where(FIELD_ROOM_SIZE);
            if (Objects.nonNull(filter.getRoomSizeMin())) {
                c.gte(filter.getRoomSizeMin());
            }
            if (Objects.nonNull(filter.getRoomSizeMax())) {
                c.lte(filter.getRoomSizeMax());
            }
            criterias.add(c);
        }

        if (Objects.nonNull(filter.getCity())) {
            criterias.add(Criteria.where(FIELD_LOCATION_CITY).is(filter.getCity()));
        }

        if (Objects.nonNull(filter.getDistrict())) {
            criterias.add(Criteria.where(FIELD_LOCATION_DISTRICT).is(filter.getDistrict()));
        }

        if (Objects.nonNull(filter.getPrice()) && Objects.nonNull(filter.getPriceOp())) {
            switch (filter.getPriceOp()) {
                case OP_LT -> criterias.add(Criteria.where(FIELD_PRICE).lt(filter.getPrice()));
                case OP_LTE -> criterias.add(Criteria.where(FIELD_PRICE).lte(filter.getPrice()));
                case OP_GT -> criterias.add(Criteria.where(FIELD_PRICE).gt(filter.getPrice()));
                case OP_GTE -> criterias.add(Criteria.where(FIELD_PRICE).gte(filter.getPrice()));
                case OP_EQ -> criterias.add(Criteria.where(FIELD_PRICE).is(filter.getPrice()));
                default -> log.warn("Invalid price operator: {}", filter.getPriceOp());
            }
        } else if (Objects.nonNull(filter.getPriceMin()) && Objects.nonNull(filter.getPriceMax())) {
            criterias.add(Criteria.where(FIELD_PRICE).gte(filter.getPriceMin()).lte(filter.getPriceMax()));
        }

        // Boolean fields
        if (Objects.nonNull(filter.getHasFan())) {
            criterias.add(Criteria.where(FIELD_HAS_FAN).is(filter.getHasFan()));
        }
        if (Objects.nonNull(filter.getHasAirConditioner())) {
            criterias.add(Criteria.where(FIELD_HAS_AIR_CONDITIONER).is(filter.getHasAirConditioner()));
        }
        if (Objects.nonNull(filter.getHasParking())) {
            criterias.add(Criteria.where(FIELD_HAS_PARKING).is(filter.getHasParking()));
        }
        if (Objects.nonNull(filter.getHasPrivateBathroom())) {
            criterias.add(Criteria.where(FIELD_HAS_PRIVATE_BATHROOM).is(filter.getHasPrivateBathroom()));
        }
        if (Objects.nonNull(filter.getHasKitchen())) {
            criterias.add(Criteria.where(FIELD_HAS_KITCHEN).is(filter.getHasKitchen()));
        }
        if (Objects.nonNull(filter.getHasFridge())) {
            criterias.add(Criteria.where(FIELD_HAS_FRIDGE).is(filter.getHasFridge()));
        }
        if (Objects.nonNull(filter.getHasWiFi())) {
            criterias.add(Criteria.where(FIELD_HAS_WIFI).is(filter.getHasWiFi()));
        }
        if (Objects.nonNull(filter.getIsSharedRoom())) {
            criterias.add(Criteria.where(FIELD_IS_SHARED_ROOM).is(filter.getIsSharedRoom()));
        }
        if (Objects.nonNull(filter.getIsPetFriendly())) {
            criterias.add(Criteria.where(FIELD_IS_PET_FRIENDLY).is(filter.getIsPetFriendly()));
        }
        if (Objects.nonNull(filter.getIsSmokingAllowed())) {
            criterias.add(Criteria.where(FIELD_IS_SMOKING_ALLOWED).is(filter.getIsSmokingAllowed()));
        }
        if (Objects.nonNull(filter.getHasElevator())) {
            criterias.add(Criteria.where(FIELD_HAS_ELEVATOR).is(filter.getHasElevator()));
        }
        if (Objects.nonNull(filter.getHasBalcony())) {
            criterias.add(Criteria.where(FIELD_HAS_BALCONY).is(filter.getHasBalcony()));
        }
        if (Objects.nonNull(filter.getHasWashingMachine())) {
            criterias.add(Criteria.where(FIELD_HAS_WASHING_MACHINE).is(filter.getHasWashingMachine()));
        }
        if (Objects.nonNull(filter.getHasTV())) {
            criterias.add(Criteria.where(FIELD_HAS_TV).is(filter.getHasTV()));
        }

        // Enum fields
        if (Objects.nonNull(filter.getRoomType())) {
            criterias.add(Criteria.where(FIELD_ROOM_TYPE).is(filter.getRoomType()));
        }
        if (Objects.nonNull(filter.getPropertyType())) {
            criterias.add(Criteria.where(FIELD_PROPERTY_TYPE).is(filter.getPropertyType()));
        }
        if (Objects.nonNull(filter.getGenderPreference())) {
            criterias.add(Criteria.where(FIELD_GENDER_PREFERENCE).is(filter.getGenderPreference()));
        }

        // Verified
        if (Objects.nonNull(filter.getVerifiedListing())) {
            criterias.add(Criteria.where(FIELD_VERIFIED_LISTING).is(filter.getVerifiedListing()));
        }

        // Date ranges
        if (Objects.nonNull(filter.getAvailableFrom())) {
            criterias.add(Criteria.where(FIELD_AVAILABLE_FROM).gte(filter.getAvailableFrom()));
        }
        if (Objects.nonNull(filter.getAvailableTo())) {
            criterias.add(Criteria.where(FIELD_AVAILABLE_TO).lte(filter.getAvailableTo()));
        }

        // minStayMonths
        if (Objects.nonNull(filter.getMinStayMonths())) {
            criterias.add(Criteria.where(FIELD_MIN_STAY_MONTHS).gte(filter.getMinStayMonths()));
        }

        // maxOccupants
        if (Objects.nonNull(filter.getMaxOccupants())) {
            criterias.add(Criteria.where(FIELD_MAX_OCCUPANTS).lte(filter.getMaxOccupants()));
        }

        // Nearby landmarks
        if (filter.getNearbyLandmarks() != null && !filter.getNearbyLandmarks().isEmpty()) {
            criterias.add(Criteria.where(FIELD_NEARBY_LANDMARKS).in(filter.getNearbyLandmarks()));
        }

        return criterias.isEmpty()
                ? new Criteria()
                : new Criteria().andOperator(criterias.toArray(new Criteria[0]));
    }

    public static Sort sort(RoomFilterDTO filter) {

        Sort.Direction direction = "desc".equalsIgnoreCase(filter.getDirection())
                ? Sort.Direction.DESC
                : Sort.Direction.ASC;

        String sortField = Objects.nonNull(filter.getSortBy()) ? filter.getSortBy() : FIELD_NAME;

        if (!ALLOWED_SORT_FIELDS.contains(sortField)) {
            throw new IllegalArgumentException("Invalid sort field: " + sortField);
        }

        return Sort.by(direction, sortField);

    }

}