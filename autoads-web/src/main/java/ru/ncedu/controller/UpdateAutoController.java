package ru.ncedu.controller;

import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import ru.ncedu.model.DataAuto;
import ru.ncedu.service.UpdateAutoService;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/auth")
public class UpdateAutoController {

    private final UpdateAutoService updateAutoService;

    @PutMapping("/{id}")
    public DataAuto updateAuto(@RequestBody DataAuto auto, @PathVariable("id") Long autoId, @RequestParam("idImage") String idImage) {
        updateAutoService.updateAuto(auto, autoId, new Long(idImage));
        return auto;
    }
}
