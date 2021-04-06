package ru.ncedu.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ncedu.model.DataAutoMultipleSearch;
import ru.ncedu.model.DataAutoSearch;
import ru.ncedu.service.SearchAutoService;

import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/auth")
public class SearchAutoController {

    private final SearchAutoService searchAutoService;

    @PostMapping("/search/page")
    public ResponseEntity<Map<String, Object>> searchAutoPage(@RequestBody DataAutoSearch dataAutoSearch,
                                                              @RequestParam(defaultValue = "0") String  page,
                                                              @RequestParam(defaultValue = "3") String size) {
        return searchAutoService.searchAutoPage(dataAutoSearch.getNameBrand(),
                dataAutoSearch.getNameModel(),
                dataAutoSearch.getStartYear(),
                dataAutoSearch.getEndYear(),
                dataAutoSearch.getColor(),
                dataAutoSearch.getStartPrice(),
                dataAutoSearch.getEndPrice(),
                dataAutoSearch.getMotorType(),
                dataAutoSearch.getStartVolume(),
                dataAutoSearch.getEndVolume(),
                dataAutoSearch.getDriveType(),
                dataAutoSearch.getTransmissionType(),
                dataAutoSearch.getBodyStyleType(),
                new Integer(page),
                new Integer(size));
    }

    @PostMapping("/multiple-search/page")
    public ResponseEntity<Map<String, Object>> multipleSearchAutoPage(@RequestBody DataAutoMultipleSearch dataAutoMultipleSearch,
                                                                      @RequestParam(defaultValue = "0") String  page,
                                                                      @RequestParam(defaultValue = "3") String size) {
        return searchAutoService.multipleSearchAutoPage(dataAutoMultipleSearch.getNameBrand(),
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

