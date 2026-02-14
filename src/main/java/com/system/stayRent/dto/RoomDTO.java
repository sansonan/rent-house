package com.system.stayRent.dto;

import com.system.stayRent.domain.enumeration.GenderPreference;
import com.system.stayRent.domain.enumeration.PropertyType;
import com.system.stayRent.domain.enumeration.RoomType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
public class RoomDTO {

    private String id;
    @NotBlank(message = "Room name is required")
    @Size(max = 100, message = "Room name must be at most 100 characters")
    private String name;
    private Double price;                  // price per month
    private Integer floor;
    private Double roomSize;              // square meters

    private LocationDTO location;

    private Boolean hasFan;
    private Boolean hasAirConditioner;
    private Boolean hasParking;
    private Boolean hasPrivateBathroom;
    private Boolean hasBalcony;
    private Boolean hasKitchen;
    private Boolean hasFridge;
    private Boolean hasWashingMachine;
    private Boolean hasTV;
    private Boolean hasWiFi;
    private Boolean hasElevator;

    private Integer maxOccupants;
    private Boolean isPetFriendly;
    private Boolean isSmokingAllowed;
    private Boolean isSharedRoom;
    private GenderPreference genderPreference;

    private RoomType roomType;
    private PropertyType propertyType;

    private Double distanceToCenter;        // optional
    private List<String> nearbyLandmarks;   // ["university", "mall"]

    private Boolean isUtilityIncluded;
    private Boolean depositRequired;
    private Integer minStayMonths;

    private Boolean hasPhotos;
    private Integer photoCount;
    private Boolean hasVideoTour;

    private Boolean verifiedListing;

    private LocalDateTime availableFrom;
    private LocalDateTime availableTo;

    private LocalDateTime createdAt;
    private LocalDateTime lastUpdated;

    private Map<String, Object> extraAttributes;
}
