package com.system.stayRent.domain;

//save room that has problems.

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Map;


@Builder
@Data
@Document(collation = "skipped_rooms")
public class SkippedRoomDocument {
    @Id
    private String id;
    private int rowNumber;
    private Map<String, Object> rowData;
    private String reason;
    private LocalDateTime uploadDate;
    private String uploadBatchId;
}
