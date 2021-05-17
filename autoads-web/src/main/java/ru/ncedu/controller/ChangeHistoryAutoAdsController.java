package ru.ncedu.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.ncedu.entity.ChangeHistoryAutoAds;
import ru.ncedu.repositories.AutoRepository;
import ru.ncedu.services.ChangeHistoryAutoAdsService;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/moderator")
public class ChangeHistoryAutoAdsController {

    private final AutoRepository autoRepository;
    private final ChangeHistoryAutoAdsService changeHistoryAutoAdsService;

    @PreAuthorize("hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    @GetMapping(path = "/change-history-auto-ads")
    @ResponseBody
    public ResponseEntity<ChangeHistoryAutoAds> getListChangeHistoryAutoAds(@RequestParam("idAuto") Long idAuto) {
        if (checkId(idAuto)) {
            return changeHistoryAutoAdsService.findAllChangeAutoAds(idAuto);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public boolean checkId(Long idAuto) {
        return autoRepository.existsById(idAuto);
    }
}
