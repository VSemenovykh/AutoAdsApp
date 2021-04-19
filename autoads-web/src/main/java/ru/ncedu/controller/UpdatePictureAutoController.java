package ru.ncedu.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.ncedu.service.UpdatePictureService;
import java.io.IOException;

import static java.util.Objects.isNull;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/auth/pictureAuto")
public class UpdatePictureAutoController {

    private final UpdatePictureService updatePictureService;

    @PutMapping("/update/{idImage}")
    public Long updatePictureAuto(@RequestParam(name = "imageFile", required = false) MultipartFile file,
                                  @PathVariable("idImage") String idImage) {
        log.info("UpdatePictureAutoController -> updatePictureAuto()");
        log.info("UpdatePictureAutoController -> MultipartFile -> isNull: " + isNull(file));
        log.info("UpdatePictureAutoController -> idImage: " + idImage);
        if (file != null && idImage != null) {
            try {
                return updatePictureService.updatePictureAuto(file, new Long(idImage));
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return new Long(idImage);
        }
    }
}
