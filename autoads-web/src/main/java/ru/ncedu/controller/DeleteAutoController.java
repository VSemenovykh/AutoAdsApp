package ru.ncedu.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import ru.ncedu.service.DeleteAutoService;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/admin")
public class DeleteAutoController {

    private final DeleteAutoService deleteAutoService;

    @PostAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity deleteAuto(@PathVariable("id") Long autoId) {
        deleteAutoService.deleteAuto(autoId);
        return ResponseEntity.ok().body("Auto with ID : " + autoId + " deleted with success!");
    }
}
