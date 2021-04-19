package ru.ncedu.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import ru.ncedu.service.DeleteAutoService;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/auth")
public class DeleteAutoController {

    private final DeleteAutoService deleteAutoService;

    @DeleteMapping("/{id}")
    public ResponseEntity deleteAuto(@PathVariable("id") Long autoId) {
        log.info("DeleteAutoController -> deleteAuto()");
        log.info("DeleteAutoController -> autoId: " + autoId);
        deleteAutoService.deleteAuto(autoId);
        return ResponseEntity.ok().body("Auto with ID : " + autoId + " deleted with success!");
    }
}
