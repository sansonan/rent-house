package com.system.stayRent.domain;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Location {
    private String country;
    private String city;
    private String district;
    private String street;
    private String fullAddress;
    //private GeoJsonPoint coordinates; // GeoJSON format for geospatial queries
}