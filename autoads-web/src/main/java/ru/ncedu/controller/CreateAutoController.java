package ru.ncedu.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import ru.ncedu.model.DataAuto;
import ru.ncedu.service.CreateAutoService;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/auth")
public class CreateAutoController {
    private final CreateAutoService createAutoService;

    @PostMapping(path = "/add")
    public ResponseEntity<DataAuto> createAuto(@RequestBody DataAuto dataAuto, @RequestParam("idImage") String idImage) {
        createAutoService.saveAuto(dataAuto, new Long(idImage));
        return new ResponseEntity<DataAuto>(HttpStatus.CREATED);
    }
}
