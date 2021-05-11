package ru.ncedu.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.ncedu.entity.CompareAuto;
import ru.ncedu.repositories.AutoRepository;
import ru.ncedu.repositories.UserRepository;
import ru.ncedu.services.CompareAutoAdsService;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/all")
public class CompareAutoAdsController {

    private final CompareAutoAdsService compareAutoAdsService;

    private final AutoRepository autoRepository;

    private final UserRepository userRepository;

    @PostAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    @GetMapping(path = "/search/compare-auto")
    @ResponseBody
    public ResponseEntity<CompareAuto> getCompareAuto(@RequestParam("idAuto") Long idAuto,
                                                      @RequestParam("idUser") Long idUser) {
        if (checkId(idAuto, idUser)) {
            CompareAuto compareAuto = compareAutoAdsService.findCompareAutoAdsByIdAuto(idAuto, idUser);
            return (compareAuto != null) ? new ResponseEntity<CompareAuto>(compareAuto, HttpStatus.OK) : new ResponseEntity<CompareAuto>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public boolean checkId(Long idAuto, Long idUser) {
        return autoRepository.existsById(idAuto) && userRepository.existsById(idUser);
    }
}
