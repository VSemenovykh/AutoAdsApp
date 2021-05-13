package ru.ncedu.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.ncedu.repositories.PictureAutoRepository;
import ru.ncedu.services.EditPictureService;

import java.io.IOException;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/all/pictureAuto")
public class EditPictureAutoController {

    private final EditPictureService editPictureService;
    private final PictureAutoRepository pictureAutoRepository;

    @PostAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    @PutMapping("/update/{idImage}")
    public ResponseEntity<Long> editPictureAuto(@RequestParam(name = "imageFile", required = false) MultipartFile file,
                                               @PathVariable("idImage") Long idImage) {
        if (checkId(idImage)) {
            if (file != null && idImage != null) {
                try {
                    return new ResponseEntity<>(editPictureService.editPictureAuto(file, idImage), HttpStatus.OK);
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            } else {
                return new ResponseEntity<>(idImage, HttpStatus.OK);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public boolean checkId(Long id){
        return pictureAutoRepository.existsById(id);
    }
}
