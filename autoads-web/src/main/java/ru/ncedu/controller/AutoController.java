package ru.ncedu.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ncedu.entity.Auto;
import ru.ncedu.model.DataAuto;
import ru.ncedu.service.AutoService;
import lombok.RequiredArgsConstructor;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/auth")
public class AutoController {

    private final AutoService autoService;

    @GetMapping(path = "/{id}")
    public ResponseEntity<Auto> getAutoById(@PathVariable("id") Long autoId) {
        log.info("AutoController -> getAutoById");
        log.info("autoId: " + autoId);
        Auto auto = autoService.findById(autoId);
        return (auto != null) ? new ResponseEntity<Auto>(auto, HttpStatus.OK) : new ResponseEntity<Auto>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(path = "/join/{id}")
    public  ResponseEntity<DataAuto>  getAutoJoinById(@PathVariable("id") long autoId) {
        log.info("AutoController -> getAutoJoinById");
        log.info("autoId: " + autoId);
        DataAuto dataAuto =  autoService.findAutoJoinById(autoId);
        return (dataAuto != null) ? new ResponseEntity<DataAuto>(dataAuto, HttpStatus.OK) : new ResponseEntity<DataAuto>(HttpStatus.NOT_FOUND);
    }
}
