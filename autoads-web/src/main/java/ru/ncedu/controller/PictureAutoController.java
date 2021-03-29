package ru.ncedu.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.ncedu.entity.PictureAuto;
import ru.ncedu.service.PictureAutoService;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class PictureAutoController {

    private final PictureAutoService pictureAutoService;

    @GetMapping(path = "/pictureAuto/{name}")
    public PictureAuto getImage(@PathVariable("name") String nameImage) {
        return (pictureAutoService.getPictureAutoByNameImage(nameImage) != null) ? pictureAutoService.getPictureAutoByNameImage(nameImage) : null;
    }

    @GetMapping(path = "/pictureAuto/idAuto/{id}")
    public PictureAuto getImage(@PathVariable("id") Long id) {
        return (pictureAutoService.findPictureAutoById(id) != null) ? pictureAutoService.findPictureAutoById(id) : null;
    }
}
