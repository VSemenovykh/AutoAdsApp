package ru.ncedu.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.ncedu.entity.CompareAuto;
import ru.ncedu.model.DataAuto;
import ru.ncedu.services.CreateListCompareAutoService;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/all")
public class CreateListCompareAutoController {

    private final CreateListCompareAutoService createListCompareAutoService;

    @PostAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    @PostMapping("/add-auto-to-compare")
    public ResponseEntity<CompareAuto> addAutoToCompare(@RequestBody DataAuto dataAuto,
                                                        @RequestParam("idUser") Long idUser) {
        CompareAuto compareAuto = createListCompareAutoService.addAutoToListCompare(dataAuto, idUser);
        return new ResponseEntity<CompareAuto>(HttpStatus.CREATED);
    }
}
