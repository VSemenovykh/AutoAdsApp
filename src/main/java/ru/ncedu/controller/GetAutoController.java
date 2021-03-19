package ru.ncedu.controller;

import org.springframework.web.bind.annotation.*;
import ru.ncedu.model.AutoJoin;
import ru.ncedu.service.AutoService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class GetAutoController {

    private final AutoService autoService;

    @GetMapping(path = {"/{id}"})
    public AutoJoin getAutoById(@PathVariable("id") long autoId) {
        AutoJoin auto = autoService.findById(autoId);
        return auto;
    }
}
