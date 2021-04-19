package ru.ncedu.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ncedu.model.DataSearchAuto;
import ru.ncedu.service.SearchAutoService;
import java.util.Map;

import static java.util.Objects.isNull;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/auth")
public class SearchAutoController {

    private final SearchAutoService searchAutoService;

    @PostMapping("/search/page")
    public ResponseEntity<Map<String, Object>> multipleSearchAutoPage(@RequestBody DataSearchAuto dataAutoMultipleSearch,
                                                                      @RequestParam(defaultValue = "0") String  page,
                                                                      @RequestParam(defaultValue = "3") String size) {
        log.info("SearchAutoController -> multipleSearchAutoPage()");
        log.info("SearchAutoController -> DataSearchAuto -> isNull: " + isNull(dataAutoMultipleSearch));
        log.info("SearchAutoController -> page: " + page);
        log.info("SearchAutoController -> size: " + size);
        return searchAutoService.searchAutoPage(dataAutoMultipleSearch.getNameBrand(),
                                                dataAutoMultipleSearch.getNameModel(),
                                                dataAutoMultipleSearch.getStartYear(),
                                                dataAutoMultipleSearch.getEndYear(),
                                                dataAutoMultipleSearch.getColor(),
                                                dataAutoMultipleSearch.getStartPrice(),
                                                dataAutoMultipleSearch.getEndPrice(),
                                                dataAutoMultipleSearch.getMotorType(),
                                                dataAutoMultipleSearch.getStartVolume(),
                                                dataAutoMultipleSearch.getEndVolume(),
                                                dataAutoMultipleSearch.getDriveType(),
                                                dataAutoMultipleSearch.getTransmissionType(),
                                                dataAutoMultipleSearch.getBodyStyleType(),
                                                new Integer(page),
                                                new Integer(size));
    }
}

