package ru.ncedu.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import ru.ncedu.model.DataAuto;
import ru.ncedu.repositoryes.AutoRepository;
import ru.ncedu.repositoryes.PictureAutoRepository;
import ru.ncedu.services.EditAutoAdsService;

import javax.validation.Valid;
import javax.validation.ValidationException;

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
    public DataAuto updateAuto(@Valid @RequestBody DataAuto auto, @PathVariable("id") Long autoId, @RequestParam("idImage") Long idImage){
        if (checkId(autoId, idImage)) {
            editAutoAdsService.editAutoAds(auto, autoId, idImage);
            return auto;
        } else {
            throw new ValidationException();
        }
    }

    public boolean checkId(Long autoId, Long idImage) {
        return (autoRepository.existsById(autoId) && pictureAutoRepository.existsById(idImage));
    }
}
