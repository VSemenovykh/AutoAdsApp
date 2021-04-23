package ru.ncedu.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.ncedu.service.DeleteListCompareAutoService;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/all")
public class DeleteListCompareAutoController {

    private final DeleteListCompareAutoService deleteListCompareAutoService;

    @PostAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    @DeleteMapping("/search/list-compare-auto/clear")
    public ResponseEntity<?> clearListCompareAuto(@RequestParam Long idUser) {
        deleteListCompareAutoService.clearListCompareAuto(idUser);
        return ResponseEntity.ok().body("List compare auto clear with success!");
    }
}
