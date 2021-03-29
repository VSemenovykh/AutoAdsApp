package ru.ncedu.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.ncedu.service.UpdatePictureService;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/pictureAuto")
public class UpdatePictureAutoController {

    private final UpdatePictureService updatePictureService;

    @PutMapping("/update/{idImage}")
    public Long updatePictureAuto(@RequestParam(name = "imageFile", required = false) MultipartFile file,
                                  @PathVariable("idImage") String idImage) throws IOException {
        Long id;
        if (file != null) {
            try {
                id = updatePictureService.updatePictureAuto(file, new Long(idImage));

                return id;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return new Long(idImage);
        }
    }
}
