package com.system.stayRent.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class RoomFilterDTO {

    @Schema(description = "Filter by floor number", example = "3")
    private Integer floor;
    private String name;
    private String type;
    private Double price;
    private String priceOp;

}
