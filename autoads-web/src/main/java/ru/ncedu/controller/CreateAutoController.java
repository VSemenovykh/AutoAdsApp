package ru.ncedu.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import ru.ncedu.model.DataAuto;
import ru.ncedu.service.CreateAutoService;

import static java.util.Objects.isNull;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/auth")
public class CreateAutoController {
    private final CreateAutoService createAutoService;

    @PostMapping(path = "/add")
    public ResponseEntity<DataAuto> createAuto(@RequestBody DataAuto dataAuto, @RequestParam("idImage") String idImage) {
        log.info("CreateAutoController -> createAuto()");
        log.info("CreateAutoController -> idImage: " + idImage);
        log.info("CreateAutoController -> DataAuto -> isNull: " + isNull(dataAuto));
        createAutoService.saveAuto(dataAuto, new Long(idImage));
        return new ResponseEntity<DataAuto>(HttpStatus.CREATED);
    }
}
