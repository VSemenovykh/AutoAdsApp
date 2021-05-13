package ru.ncedu.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.ncedu.entity.PictureAuto;
import ru.ncedu.repositories.PictureAutoRepository;
import ru.ncedu.services.PictureAutoService;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/all")
public class PictureAutoController {

    private final PictureAutoService pictureAutoService;
    private final PictureAutoRepository pictureAutoRepository;

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    @GetMapping(path = "/pictureAuto/{name}")
    @ResponseBody
    public ResponseEntity<PictureAuto> getImageByName(@PathVariable("name") String nameImage) {
        PictureAuto pictureAuto = pictureAutoService.getPictureAutoByNameImage(nameImage);
        return (pictureAuto != null) ? new ResponseEntity<PictureAuto>(pictureAuto, HttpStatus.OK) : new ResponseEntity<PictureAuto>(HttpStatus.NOT_FOUND);
    }

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    @GetMapping(path = "/pictureAuto/idAuto/{id}")
    @ResponseBody
    public ResponseEntity<PictureAuto> getImageById(@PathVariable("id") Long id) {
        if (checkId(id)) {
            PictureAuto pictureAuto = pictureAutoService.findPictureAutoById(id);
            return (pictureAuto != null) ? new ResponseEntity<PictureAuto>(pictureAuto, HttpStatus.OK) : new ResponseEntity<PictureAuto>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public boolean checkId(Long id) {
        return pictureAutoRepository.existsById(id);
    }
}
