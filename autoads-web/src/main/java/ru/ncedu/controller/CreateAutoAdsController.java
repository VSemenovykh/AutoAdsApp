package ru.ncedu.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.ncedu.model.DataAuto;
import ru.ncedu.services.CreateAutoAdsService;
import ru.ncedu.services.ValidDataAutoAds;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/all")
public class CreateAutoAdsController {

    private final CreateAutoAdsService createAutoAdsService;
    private final ValidDataAutoAds validDataAutoAds;

    @PostAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    @PostMapping(path = "/add")
    @ResponseBody
    public ResponseEntity<DataAuto> createAuto(@Valid @RequestBody DataAuto dataAuto, @RequestParam("idImage") Long idImage) {
        if (validDataAutoAds.checkDataAutoAds(dataAuto)) {

            return createAutoAdsService.saveAuto(dataAuto, idImage);
        } else {
            return new ResponseEntity<DataAuto>(HttpStatus.BAD_REQUEST);
        }
    }
}



