package com.system.stayRent.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomFilterDTO {

    @Schema(description = "Filter by floor number", example = "3")
    private Integer floor;
    private String name;
    private String type;
    private Double price;
    private Double maxPrice;
    private Double minPrice;
    private String priceOp;

    private int size =10;
    private int page = 0;

    @Schema(description = "sort by field name ")
    private String sortBy;

    @Schema(description = "Sort direction : asc , desc , example = 'asc' ")
    private String direction;


}
