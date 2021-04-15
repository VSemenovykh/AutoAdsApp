package ru.ncedu.controller;

import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import ru.ncedu.model.DataAuto;
import ru.ncedu.service.CreateAutoService;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/auth")
public class CreateAutoController {
    private final CreateAutoService createAutoService;

    @PostMapping(path = "/add")
    public DataAuto createAuto(@RequestBody DataAuto dataAuto, @RequestParam("idImage") String idImage) {
        return createAutoService.saveAuto(dataAuto, new Long(idImage));
    }
}
