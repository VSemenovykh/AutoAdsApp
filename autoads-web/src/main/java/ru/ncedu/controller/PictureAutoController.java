package ru.ncedu.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ncedu.entity.PictureAuto;
import ru.ncedu.service.PictureAutoService;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/auth")
public class PictureAutoController {

    private final PictureAutoService pictureAutoService;

    @GetMapping(path = "/pictureAuto/{name}")
    public ResponseEntity<PictureAuto> getImage(@PathVariable("name") String nameImage) {
        PictureAuto pictureAuto = pictureAutoService.getPictureAutoByNameImage(nameImage);
        return (pictureAuto != null) ? new ResponseEntity<PictureAuto>(pictureAuto, HttpStatus.OK) : new ResponseEntity<PictureAuto>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(path = "/pictureAuto/idAuto/{id}")
    public ResponseEntity<PictureAuto> getImage(@PathVariable("id") Long id) {
        PictureAuto pictureAuto = pictureAutoService.findPictureAutoById(id);
        return (pictureAuto != null) ? new ResponseEntity<PictureAuto>(pictureAuto, HttpStatus.OK) : new ResponseEntity<PictureAuto>(HttpStatus.NOT_FOUND);
    }
}
