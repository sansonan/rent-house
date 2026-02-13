package com.system.stayRent.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoomImportSummaryResponseDTO {
    private int inserted;
    private int skipped;
    private List<Integer> skippedRow;
    private Map<Integer, String> reasons;

}
