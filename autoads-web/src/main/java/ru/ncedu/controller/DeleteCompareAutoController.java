package ru.ncedu.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ncedu.service.DeleteCompareAutoService;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/auth")
public class DeleteCompareAutoController {

    private final DeleteCompareAutoService deleteCompareAutoService;

    @DeleteMapping("/search/compare-auto/{id}")
    public ResponseEntity deleteAuto(@PathVariable("id") Long autoId) {
        log.info("id auto: " + autoId);
        deleteCompareAutoService.deleteCompareAuto(autoId);
        return ResponseEntity.ok().body("Auto with ID : " + autoId + " deleted with success!");
    }
}
