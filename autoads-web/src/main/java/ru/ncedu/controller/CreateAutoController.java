package ru.ncedu.controller;

import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import ru.ncedu.model.AutoJoin;
import ru.ncedu.service.CreateAutoService;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class CreateAutoController {
    private final CreateAutoService createAutoService;

    @PostMapping(path = "/add")
    public AutoJoin createAuto(@RequestBody AutoJoin autoJoin, @RequestParam("idImage") String idImage) {
        return createAutoService.saveAuto(autoJoin, new Long(idImage));
    }
}
