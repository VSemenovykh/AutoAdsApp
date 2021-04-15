package ru.ncedu.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ncedu.service.DeleteCompareAutoService;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/auth")
public class DeleteCompareAutoController {

    private final DeleteCompareAutoService deleteCompareAutoService;

    @DeleteMapping("/search/list-compare-auto/{id}")
    public ResponseEntity<?> deleteAuto(@PathVariable("id") Long autoId) {
        deleteCompareAutoService.deleteCompareAuto(autoId);
        return ResponseEntity.ok().body("Auto with ID : " + autoId + " deleted with success!");
    }
}
