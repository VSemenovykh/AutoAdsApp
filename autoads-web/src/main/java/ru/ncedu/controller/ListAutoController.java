package ru.ncedu.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.ncedu.entity.Auto;
import ru.ncedu.implement.AutoAdsAdsServiceImpl;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/all")
public class ListAutoController {

    private final AutoAdsAdsServiceImpl autoRepository;

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    @GetMapping("/auto")
    @ResponseBody
    public ResponseEntity<List<Auto>> getListAuto() {
        return autoRepository.findAll();
    }

}
