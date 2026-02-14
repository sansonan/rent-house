package com.system.stayRent.constant;

import java.util.List;

public final class RoomConstants {

    private RoomConstants() {}

    // Basic Fields
    public static final String FIELD_ID = "id";
    public static final String FIELD_NAME = "name";
    public static final String FIELD_PRICE = "price";
    public static final String FIELD_FLOOR = "floor";
    public static final String FIELD_ROOM_SIZE = "roomSize";

    // Location
    public static final String FIELD_LOCATION_CITY = "location.city";
    public static final String FIELD_LOCATION_DISTRICT = "location.district";
    public static final String FIELD_LOCATION_COORDINATES = "location.coordinates";

    // Amenities & Features
    public static final String FIELD_HAS_FAN = "hasFan";
    public static final String FIELD_HAS_AIR_CONDITIONER = "hasAirConditioner";
    public static final String FIELD_HAS_PARKING = "hasParking";
    public static final String FIELD_HAS_PRIVATE_BATHROOM = "hasPrivateBathroom";
    public static final String FIELD_HAS_BALCONY = "hasBalcony";
    public static final String FIELD_HAS_KITCHEN = "hasKitchen";
    public static final String FIELD_HAS_FRIDGE = "hasFridge";
    public static final String FIELD_HAS_WASHING_MACHINE = "hasWashingMachine";
    public static final String FIELD_HAS_TV = "hasTV";
    public static final String FIELD_HAS_WIFI = "hasWiFi";
    public static final String FIELD_HAS_ELEVATOR = "hasElevator";

    // Occupancy & Preferences
    public static final String FIELD_MAX_OCCUPANTS = "maxOccupants";
    public static final String FIELD_IS_PET_FRIENDLY = "isPetFriendly";
    public static final String FIELD_IS_SMOKING_ALLOWED = "isSmokingAllowed";
    public static final String FIELD_IS_SHARED_ROOM = "isSharedRoom";

    // Enums
    public static final String FIELD_GENDER_PREFERENCE = "genderPreference";
    public static final String FIELD_ROOM_TYPE = "roomType";
    public static final String FIELD_PROPERTY_TYPE = "propertyType";

    // Optional Info
    public static final String FIELD_DISTANCE_TO_CENTER = "distanceToCenter";
    public static final String FIELD_NEARBY_LANDMARKS = "nearbyLandmarks";

    // Listing & Rental Policy
    public static final String FIELD_IS_UTILITY_INCLUDED = "isUtilityIncluded";
    public static final String FIELD_DEPOSIT_REQUIRED = "depositRequired";
    public static final String FIELD_MIN_STAY_MONTHS = "minStayMonths";

    public static final String FIELD_HAS_PHOTOS = "hasPhotos";
    public static final String FIELD_PHOTO_COUNT = "photoCount";
    public static final String FIELD_HAS_VIDEO_TOUR = "hasVideoTour";
    public static final String FIELD_VERIFIED_LISTING = "verifiedListing";

    // Dates
    public static final String FIELD_AVAILABLE_FROM = "availableFrom";
    public static final String FIELD_AVAILABLE_TO = "availableTo";
    public static final String FIELD_CREATED_AT = "createdAt";
    public static final String FIELD_LAST_UPDATED = "lastUpdated";

    // Operators
    public static final String OP_LT = "lt";
    public static final String OP_LTE = "lte";
    public static final String OP_GT = "gt";
    public static final String OP_GTE = "gte";
    public static final String OP_EQ = "eq";

    // Sort allow-list
    public static final List<String> ALLOWED_SORT_FIELDS = List.of(
            FIELD_NAME,
            FIELD_PRICE,
            FIELD_FLOOR,
            FIELD_ROOM_SIZE,
            FIELD_LOCATION_CITY,
            FIELD_LOCATION_DISTRICT,
            FIELD_VERIFIED_LISTING,
            FIELD_CREATED_AT
    );

}
