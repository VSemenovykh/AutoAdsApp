package ru.ncedu.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import ru.ncedu.model.DataAuto;
import ru.ncedu.services.EditAutoService;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/all")
public class EditAutoController {

    private final EditAutoService editAutoService;

    @PostAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public DataAuto updateAuto(@RequestBody DataAuto auto, @PathVariable("id") Long autoId, @RequestParam("idImage") String idImage) {
        editAutoService.editAuto(auto, autoId, new Long(idImage));
        return auto;
    }
}
