package ru.ncedu.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.ncedu.entity.Auto;
import ru.ncedu.model.DataAuto;
import ru.ncedu.repositories.AutoRepository;
import ru.ncedu.services.AutoAdsService;
import lombok.RequiredArgsConstructor;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/all")
public class AutoController {

    private final AutoAdsService autoAdsService;

    private final AutoRepository autoRepository;

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    @GetMapping(path = "/{id}")
    @ResponseBody
    public ResponseEntity<Auto> getAutoById(@PathVariable("id") Long autoId) {
        if (checkId(autoId)) {
            Auto auto = autoAdsService.findAutoById(autoId);
            return (auto != null) ? new ResponseEntity<Auto>(auto, HttpStatus.OK) : new ResponseEntity<Auto>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    @GetMapping(path = "/join/{id}")
    @ResponseBody
    public ResponseEntity<DataAuto> getAutoJoinById(@PathVariable("id") Long autoId) {
        if (checkId(autoId)) {
            DataAuto dataAuto = autoAdsService.findAutoAdsById(autoId);
            return (dataAuto != null) ? new ResponseEntity<DataAuto>(dataAuto, HttpStatus.OK) : new ResponseEntity<DataAuto>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public boolean checkId(Long id) {
        return autoRepository.existsById(id);
    }
}
