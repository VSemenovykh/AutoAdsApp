package ru.ncedu.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.ncedu.model.DataAuto;
import ru.ncedu.repositories.AutoRepository;
import ru.ncedu.repositories.PictureAutoRepository;
import ru.ncedu.services.EditAutoAdsService;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/all")
public class EditAutoAdsController {

    private final EditAutoAdsService editAutoAdsService;
    private final AutoRepository autoRepository;
    private final PictureAutoRepository pictureAutoRepository;

    @PostAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<DataAuto> editAutoAds(@Valid @RequestBody DataAuto auto, @PathVariable("id") Long autoId, @RequestParam("idImage") Long idImage) {
        if (checkId(autoId, idImage)) {
            editAutoAdsService.editAutoAds(auto, autoId, idImage);
            return new ResponseEntity<>(auto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public boolean checkId(Long autoId, Long idImage) {
        return (autoRepository.existsById(autoId) && pictureAutoRepository.existsById(idImage));
    }
}
