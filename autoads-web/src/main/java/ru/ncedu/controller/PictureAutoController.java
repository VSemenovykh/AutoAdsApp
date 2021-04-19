package ru.ncedu.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ncedu.entity.PictureAuto;
import ru.ncedu.service.PictureAutoService;

import static java.util.Objects.isNull;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/auth")
public class PictureAutoController {

    private final PictureAutoService pictureAutoService;

    @GetMapping(path = "/pictureAuto/{name}")
    public ResponseEntity<PictureAuto> getImageByName(@PathVariable("name") String nameImage) {
        log.info("PictureAutoController -> getImage()");
        log.info("PictureAutoController -> nameImage: " + nameImage);
        PictureAuto pictureAuto = pictureAutoService.getPictureAutoByNameImage(nameImage);
        log.info("PictureAutoController -> PictureAuto -> isNull: " + isNull(pictureAuto));
        return (pictureAuto != null) ? new ResponseEntity<PictureAuto>(pictureAuto, HttpStatus.OK) : new ResponseEntity<PictureAuto>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(path = "/pictureAuto/idAuto/{id}")
    public ResponseEntity<PictureAuto> getImageById(@PathVariable("id") Long id) {
        log.info("PictureAutoController -> getImage()");
        log.info("PictureAutoController -> id: " + id);
        PictureAuto pictureAuto = pictureAutoService.findPictureAutoById(id);
        log.info("PictureAutoController -> PictureAuto -> isNull: " + isNull(pictureAuto));
        return (pictureAuto != null) ? new ResponseEntity<PictureAuto>(pictureAuto, HttpStatus.OK) : new ResponseEntity<PictureAuto>(HttpStatus.NOT_FOUND);
    }
}
