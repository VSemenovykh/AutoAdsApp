package ru.ncedu.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ncedu.entity.CompareAuto;
import ru.ncedu.model.DataAuto;
import ru.ncedu.service.CreateListCompareAutoService;

import static java.util.Objects.isNull;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/auth")
public class CreateListCompareAutoController {

    private final CreateListCompareAutoService createListCompareAutoService;

    @PostMapping(path = "/add-auto-to-compare")
    public ResponseEntity<CompareAuto> addAutoToCompare(@RequestBody DataAuto dataAuto) {
        log.info("CreateListCompareAutoController -> addAutoToCompare()");
        log.info("CreateListCompareAutoController -> DataAuto -> isNulL:" + isNull(dataAuto));
        CompareAuto compareAuto = createListCompareAutoService.addAutoToListCompare(dataAuto);
        return new ResponseEntity<CompareAuto>(HttpStatus.CREATED);
    }
}
