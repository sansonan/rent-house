package com.system.stayRent.service;

import com.system.stayRent.dto.RoomImportSummaryResponseDTO;
import org.springframework.http.codec.multipart.FilePart;
import reactor.core.publisher.Mono;

public interface RoomImportService {
    Mono<RoomImportSummaryResponseDTO>  importRoom(FilePart filePart);
}
