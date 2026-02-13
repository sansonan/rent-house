package com.system.stayRent.service.impl;

import com.system.stayRent.domain.Room;
import com.system.stayRent.domain.SkippedRoomDocument;
import com.system.stayRent.dto.RoomImportSummaryResponseDTO;
import com.system.stayRent.repository.RoomRepository;
import com.system.stayRent.repository.SkippedRoomDocumentRepository;
import com.system.stayRent.service.RoomImportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Mono;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;


@Service
@Slf4j
@RequiredArgsConstructor
public class RoomImportServiceImpl implements RoomImportService{

    private final SkippedRoomDocumentRepository skippedRoomDocumentRepository;
    private final RoomRepository roomRepository;

    @Override
    public Mono<RoomImportSummaryResponseDTO> importRoom(FilePart filePart) {
        return filePart.content() // Flx<DataBuffer>
                .map(dataBuffer ->{
                    byte[] bytes = new byte[dataBuffer.readableByteCount()];
                    dataBuffer.read(bytes);
                    DataBufferUtils.release(dataBuffer);

                    return new ByteArrayInputStream(bytes);
                })
                .next()
                .flatMap(inputStream -> parseAndSaveRooms(inputStream));
    }

	/*

	 load content from excel file as streaming data
	 convert into byteArray


	 */

    private Mono<RoomImportSummaryResponseDTO> parseAndSaveRooms(ByteArrayInputStream inputStream) {
        // workbook = new XSS

        String batchId = UUID.randomUUID().toString();

        try(Workbook workbook = new XSSFWorkbook(inputStream)) {
            Sheet sheet = workbook.getSheetAt(0);
            List<Room> validRoom = new ArrayList<>();
            List<Integer> skippedRow = new ArrayList<>();
            Map<Integer, String> reasons = new HashMap<>();
            List<SkippedRoomDocument> skippedRoomDocuments = new ArrayList<>();

            for(int i = 1; i<= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                int displayRow = i+1;

                if(row == null) {
                    skippedRow.add(displayRow);
                    reasons.put(displayRow, "Empty Row");
                    skippedRoomDocuments.add(buildSkippedRoomDocument(displayRow, Collections.emptyMap(), "Empty Row", batchId));
                    continue;
                }

                String name = getString(row.getCell(0));
                Double price = getDouble(row.getCell(1));
                Double floorValue = getDouble(row.getCell(2));
                Integer floor = (floorValue != null)? floorValue.intValue() : null;
                String type = getString(row.getCell(3));

                Map<String, Object> rowData = new HashMap<>();
                rowData.put("name", name);
                rowData.put("price", price);
                rowData.put("floor", floor);
                rowData.put("type", type);

                String reason = null;
                if(!StringUtils.hasText(name)) {
                    reason = "Missing room name";
                }
                else if (price == null) {
                    reason = "Missing or invalid price";
                }
                else if (floor == null) {
                    reason = "Missing or invalid floor";
                }
                else if (type == null) {
                    reason = "Missing or invalid type";
                }

                if(reason != null) {
                    skippedRow.add(displayRow);
                    reasons.put(displayRow, reason);
                    skippedRoomDocuments.add(buildSkippedRoomDocument(displayRow, rowData, reason, batchId));
                    continue;
                }
                // Valid Room

                Room room = new Room();
                room.setName(name);
                //room.setAttributes(rowData);

                log.debug("Prepared to save room: {}", room);
                validRoom.add(room);
            }

            log.info("Valid rooms to save: {}", validRoom.size());

            return skippedRoomDocumentRepository.saveAll(skippedRoomDocuments)
                    .thenMany(roomRepository.saveAll(validRoom)
                            .doOnNext(r -> log.info("Saved room: {}", r))
                            .doOnError(err -> log.error("Error saving room: {}", err.getMessage(), err))
                    )
                    .collectList()
                    .map(saved -> new RoomImportSummaryResponseDTO(saved.size(), skippedRoomDocuments.size(), skippedRow, reasons));


        } catch (IOException e) {
            log.error("Fail to parse Excel", e);
            return Mono.error(new RuntimeException("Fail to read Excell file", e));
        }

    }

    private SkippedRoomDocument buildSkippedRoomDocument(
            int rowNumber,
            Map<String, Object> rowData,
            String reason, String batchId) {

        return SkippedRoomDocument.builder()
                .rowNumber(rowNumber)
                .rowData(rowData)
                .reason(reason)
                .uploadBatchId(batchId)
                .uploadDate(LocalDateTime.now())
                .build();

    }

    private String getString(Cell cell) {
        return cell == null ? null : cell.getStringCellValue();
    }

    private Double getDouble(Cell cell) {
        try {
            return cell == null ? null : cell.getNumericCellValue();
        }catch (Exception e) {
            return null;
        }

    }

}
