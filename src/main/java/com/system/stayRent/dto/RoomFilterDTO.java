package com.system.stayRent.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.system.stayRent.domain.enumeration.GenderPreference;
import com.system.stayRent.domain.enumeration.PropertyType;
import com.system.stayRent.domain.enumeration.RoomType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class RoomFilterDTO {
    @Schema(description = "Exact match for room name", example = "Cozy Studio Apartment")
    private String name;

    @Schema(description = "Exact match for floor number", example = "3")
    private Integer floor;

    @Schema(description = "Price filter with a specific operator (lt, lte, gt, gte, eq)", example = "500")
    private Double price;

    @Schema(description = "Price operator: lt, lte, gt, gte, eq", example = "lt")
    private String priceOp;

    @Schema(description = "Minimum price for range search", example = "200")
    private Double priceMin;

    @Schema(description = "Maximum price for range search", example = "1000")
    private Double priceMax;

    @Schema(description = "Minimum room size in square meters", example = "20")
    private Double roomSizeMin;

    @Schema(description = "Maximum room size in square meters", example = "50")
    private Double roomSizeMax;

    @Schema(description = "City where the room is located", example = "Phnom Penh")
    private String city;

    @Schema(description = "District where the room is located", example = "Chamkarmon")
    private String district;

    @Schema(description = "Has fan", example = "true")
    private Boolean hasFan;

    @Schema(description = "Has air conditioner", example = "true")
    private Boolean hasAirConditioner;

    @Schema(description = "Has parking", example = "true")
    private Boolean hasParking;

    @Schema(description = "Has private bathroom", example = "true")
    private Boolean hasPrivateBathroom;

    @Schema(description = "Has balcony", example = "false")
    private Boolean hasBalcony;

    @Schema(description = "Has kitchen", example = "true")
    private Boolean hasKitchen;

    @Schema(description = "Has fridge", example = "true")
    private Boolean hasFridge;

    @Schema(description = "Has washing machine", example = "false")
    private Boolean hasWashingMachine;

    @Schema(description = "Has TV", example = "true")
    private Boolean hasTV;

    @Schema(description = "Has WiFi", example = "true")
    private Boolean hasWiFi;

    @Schema(description = "Has elevator", example = "false")
    private Boolean hasElevator;

    @Schema(description = "Pet friendly", example = "true")
    private Boolean isPetFriendly;

    @Schema(description = "Smoking allowed", example = "false")
    private Boolean isSmokingAllowed;

    @Schema(description = "Is shared room", example = "false")
    private Boolean isSharedRoom;

    @Schema(description = "Gender preference for tenant", example = "FEMALE")
    private GenderPreference genderPreference;
//
    @Schema(description = "Room type", example = "STUDIO")
    private RoomType roomType;
//
    @Schema(description = "Property type", example = "APARTMENT")
    private PropertyType propertyType;

    @Schema(description = "Utility included in price", example = "true")
    private Boolean isUtilityIncluded;

    @Schema(description = "Deposit required", example = "false")
    private Boolean depositRequired;

    @Schema(description = "Minimum stay in months (>= filter)", example = "6")
    private Integer minStayMonths;

    @Schema(description = "Maximum occupants allowed (<= filter)", example = "4")
    private Integer maxOccupants;

    @Schema(description = "Available from date (>= filter)", example = "2025-09-01T00:00:00")
    private LocalDateTime availableFrom;

    @Schema(description = "Available to date (<= filter)", example = "2025-12-31T00:00:00")
    private LocalDateTime availableTo;

    @Schema(description = "Nearby landmarks (contains match)", example = "[\"university\", \"mall\"]")
    private List<String> nearbyLandmarks;

    @Schema(description = "Listing is verified", example = "true")
    private Boolean verifiedListing;

    @Schema(description = "Sort by field name (ex: name, floor, price, createdAt)", example = "name")
    private String sortBy;

    @Schema(description = "Sort direction: asc or desc", example = "asc")
    private String direction;

    @Schema(description = "Page number (for pagination)", example = "0")
    private Integer page;

    @Schema(description = "Page size (for pagination)", example = "20")
    private Integer size;

    @Schema(description = "Longitude for geo search", example = "104.9211")
    private Double nearLng;

    @Schema(description = "Latitude for geo search", example = "11.5564")
    private Double nearLat;

    @Schema(description = "Max distance in meters for geo search", example = "5000")
    private Double maxDistanceMeters;

    private String provinceCode;
    private String districtCode;
    private String communeCode;
    private String villageCode;

}
