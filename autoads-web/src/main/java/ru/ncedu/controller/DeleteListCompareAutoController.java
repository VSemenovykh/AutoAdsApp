package ru.ncedu.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ncedu.service.DeleteListCompareAutoService;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/auth")
public class DeleteListCompareAutoController {

    private final DeleteListCompareAutoService deleteListCompareAutoService;

    @DeleteMapping("/search/list-compare-auto/clear")
    public ResponseEntity<?> clearListCompareAuto() {
        log.info("DeleteListCompareAutoController -> clearListCompareAuto()");
        deleteListCompareAutoService.clearListCompareAuto();
        return ResponseEntity.ok().body("List compare auto clear with success!");
    }
}
