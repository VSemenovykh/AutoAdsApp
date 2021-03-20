package ru.ncedu.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ncedu.service.AutoService;
import lombok.RequiredArgsConstructor;
import ru.ncedu.service.DeleteService;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class DeleteAutoController {
    private  final DeleteService deleteService;

    @DeleteMapping("/{id}")
    public ResponseEntity deleteAuto(@PathVariable("id") Long autoId) {
        deleteService.deleteAuto(autoId);
        return ResponseEntity.ok().body("Auto with ID : " + autoId + " deleted with success!");
    }
}
