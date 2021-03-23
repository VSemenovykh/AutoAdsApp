package ru.ncedu.controller;

import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import ru.ncedu.entity.Auto;
import ru.ncedu.model.AutoJoin;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class CreateAutoController {
    private final CreateService createService;

    @PostMapping(path = "/add")
    public AutoJoin createAuto(@RequestBody Auto auto) {
        return createService.saveAuto(auto);
    }
}
