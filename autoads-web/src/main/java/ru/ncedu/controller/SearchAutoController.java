package ru.ncedu.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.ncedu.model.DataSearchAuto;
import ru.ncedu.services.SearchAutoService;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/all")
public class SearchAutoController {

    private final SearchAutoService searchAutoService;

    @PostAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    @PostMapping("/search/page")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> searchAutoPage(@RequestBody DataSearchAuto dataAutoMultipleSearch,
                                                              @RequestParam(defaultValue = "0") String page,
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

