package ru.ncedu.controller;

import org.springframework.web.bind.annotation.*;
import ru.ncedu.entity.Auto;
import lombok.RequiredArgsConstructor;
import ru.ncedu.service.UpdateService;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class UpdateAutoController {

    private final UpdateService updateService;

    @PutMapping("/{id}")
    public Auto updateAuto(@RequestBody Auto auto, @PathVariable("id") Long autoId) {
        updateService.updateAuto(auto, autoId);
        return auto;
    }
}
