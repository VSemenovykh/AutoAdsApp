package ru.ncedu.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.ncedu.entity.ImageAuto;
import ru.ncedu.service.ImageAutoService;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class ImageAutoController {

    private final ImageAutoService imageAutoService;

    @GetMapping(path = "/imageAuto/{name}")
    public ImageAuto getImage(@PathVariable("name") String nameImage) {
        log.info("name image:" + nameImage);
        ImageAuto imageAuto = imageAutoService.getImageAutoByNameImage(nameImage);
        return imageAuto;
    }

    @GetMapping(path = "/imageAuto/idAuto/{id}")
    public ImageAuto getImage(@PathVariable("id") Long id) {
        log.info("id:" + id);
        ImageAuto imageAuto = imageAutoService.findImageAutoById(id);
        return imageAuto;
    }
}
