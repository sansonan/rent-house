package com.system.stayRent.repository;

import com.system.stayRent.domain.SkippedRoomDocument;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface SkippedRoomDocumentRepository extends ReactiveCrudRepository<SkippedRoomDocument, String> {
}
