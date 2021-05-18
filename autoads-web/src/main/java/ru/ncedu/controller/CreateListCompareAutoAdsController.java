package ru.ncedu.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.ncedu.entity.CompareAuto;
import ru.ncedu.model.DataAuto;
import ru.ncedu.repositories.UserRepository;
import ru.ncedu.services.CreateListCompareAutoAdsService;
import ru.ncedu.services.ValidDataAutoAds;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/all")
public class CreateListCompareAutoAdsController {

    private final CreateListCompareAutoAdsService createListCompareAutoAdsService;
    private final UserRepository userRepository;
    private final ValidDataAutoAds validDataAutoAds;

    @PostAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    @PostMapping("/add-auto-to-compare")
    @ResponseBody
    public ResponseEntity<CompareAuto> addAutoToCompare(@Valid @RequestBody DataAuto dataAuto,
                                                        @RequestParam("idUser") Long idUser) {

        if (validDataAutoAds.checkDataAutoAds(dataAuto) && checkId(idUser)) {
            CompareAuto compareAuto = createListCompareAutoAdsService.addAutoAdsToListCompare(dataAuto, idUser);
            log.info("idUser: " + idUser);
            return new ResponseEntity<CompareAuto>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<CompareAuto>(HttpStatus.BAD_REQUEST);
        }
    }

    public boolean checkId(Long id) {
        return userRepository.existsById(id);
    }
}
