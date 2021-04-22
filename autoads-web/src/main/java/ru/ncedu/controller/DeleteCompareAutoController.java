package ru.ncedu.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.ncedu.service.DeleteCompareAutoService;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/all")
public class DeleteCompareAutoController {

    private final DeleteCompareAutoService deleteCompareAutoService;

    @PostAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    @DeleteMapping("/search/list-compare-auto/{id}")
    public ResponseEntity<?> deleteCompareAuto(@PathVariable("id") Long autoId,
                                               @RequestParam("idUser") Long idUser) {
        deleteCompareAutoService.deleteCompareAuto(autoId, idUser);
        return ResponseEntity.ok().body("Auto with ID : " + autoId + " deleted with success!");
    }
}
