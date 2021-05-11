package ru.ncedu.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.ncedu.model.*;
import ru.ncedu.services.SearchAutoAdsService;
import ru.ncedu.services.ValidDataSearchAutoAds;

import javax.validation.Valid;
import java.util.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/all")
public class SearchAutoAdsController {

    private final SearchAutoAdsService searchAutoAdsService;

    private final ValidDataSearchAutoAds validDataSearchAutoAds;

    @PostAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    @PostMapping("/search")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> searchAutoAds(@Valid @RequestBody DataSearchAuto dataSearchAuto,
                                                             @RequestParam(defaultValue = "0") int page,
                                                             @RequestParam(defaultValue = "3") int size) {
        if(validDataSearchAutoAds.checkDataSearchAutoAds(dataSearchAuto)){
            return searchAutoAdsService.searchAutoAds(dataSearchAuto.getNameBrand(),
                    dataSearchAuto.getNameModel(),
                    dataSearchAuto.getStartYear(),
                    dataSearchAuto.getEndYear(),
                    dataSearchAuto.getColor(),
                    dataSearchAuto.getStartPrice(),
                    dataSearchAuto.getEndPrice(),
                    dataSearchAuto.getMotorType(),
                    dataSearchAuto.getStartVolume(),
                    dataSearchAuto.getEndVolume(),
                    dataSearchAuto.getDriveType(),
                    dataSearchAuto.getTransmissionType(),
                    dataSearchAuto.getBodyStyleType(),
                    page,
                    size);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}

