package ru.ncedu.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ncedu.model.DataAutoSearch;
import ru.ncedu.service.SearchAutoService;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/auth")
public class SearchAutoController {

    private final SearchAutoService searchAutoService;

    @PostMapping("/search/page")
    public ResponseEntity<Map<String, Object>> multipleSearchAutoPage(@RequestBody DataAutoSearch dataAutoMultipleSearch,
                                                                      @RequestParam(defaultValue = "0") String  page,
                                                                      @RequestParam(defaultValue = "3") String size) {
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

