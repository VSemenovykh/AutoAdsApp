package ru.ncedu.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import ru.ncedu.model.DataAuto;
import ru.ncedu.service.CreateAutoService;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/all")
public class CreateAutoController {
    private final CreateAutoService createAutoService;

    @PostAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    @PostMapping(path = "/add")
    public ResponseEntity<DataAuto> createAuto(@RequestBody DataAuto dataAuto, @RequestParam("idImage") String idImage) {
        createAutoService.saveAuto(dataAuto, new Long(idImage));
        return new ResponseEntity<DataAuto>(HttpStatus.CREATED);
    }
}
