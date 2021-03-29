package ru.ncedu.controller;

import org.springframework.web.bind.annotation.*;
import ru.ncedu.entity.Auto;
import ru.ncedu.model.AutoJoin;
import ru.ncedu.service.AutoService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class AutoController {

    private final AutoService autoService;

    @GetMapping(path = "/{id}")
    public Auto getAutoById(@PathVariable("id") long autoId) {
        return  autoService.findById(autoId);
    }

    @GetMapping(path = "/join/{id}")
    public AutoJoin getAutoJoinById(@PathVariable("id") long autoId) {
        return  autoService.findAutoJoinById(autoId);
    }
}
