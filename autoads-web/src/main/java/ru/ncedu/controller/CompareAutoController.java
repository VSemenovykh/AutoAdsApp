package ru.ncedu.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.ncedu.entity.CompareAuto;
import ru.ncedu.service.CompareAutoService;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/all")
public class CompareAutoController {

    private final CompareAutoService compareAutoService;

    @PostAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    @GetMapping(path = "/search/compare-auto")
    public ResponseEntity<CompareAuto> getCompareAuto(@RequestParam("idAuto") Long idAuto,
                                                      @RequestParam("idUser") Long idUser) {
        CompareAuto compareAuto = compareAutoService.findCompareAutoByIdAuto(idAuto, idUser);
        return (compareAuto != null) ? new ResponseEntity<CompareAuto>(compareAuto, HttpStatus.OK) : new ResponseEntity<CompareAuto>(HttpStatus.NOT_FOUND);
    }
}
