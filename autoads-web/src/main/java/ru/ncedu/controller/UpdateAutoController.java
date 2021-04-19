package ru.ncedu.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import ru.ncedu.model.DataAuto;
import ru.ncedu.service.UpdateAutoService;

import static java.util.Objects.isNull;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/auth")
public class UpdateAutoController {

    private final UpdateAutoService updateAutoService;

    @PutMapping("/{id}")
    public DataAuto updateAuto(@RequestBody DataAuto auto, @PathVariable("id") Long autoId, @RequestParam("idImage") String idImage) {
        log.info("UpdateAutoController -> updateAuto()");
        log.info("UpdateAutoController -> DataAuto -> isNull: " + isNull(auto));
        log.info("UpdateAutoController -> autoId: " + autoId);
        log.info("UpdateAutoController -> idImage: " + idImage);
        updateAutoService.updateAuto(auto, autoId, new Long(idImage));
        return auto;
    }
}
