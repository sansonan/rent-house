package com.system.stayRent.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Paginated response wrapper")
public class PageDTO<T> {
    @Schema(description = "Current page number (0-based)")
    private int page;

    @Schema(description = "Number of records per page")
    private int size;

    @Schema(description = "Total number of records")
    private long totalElements;

    @Schema(description = "Total number of pages")
    private int totalPages;

    @Schema(description = "Current page of data list")
    private List<T> content;
}
