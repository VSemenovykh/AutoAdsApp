package ru.ncedu.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.ncedu.services.EditPictureService;
import java.io.IOException;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/all/pictureAuto")
public class EditPictureAutoController {

    private final EditPictureService editPictureService;

    @PostAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    @PutMapping("/update/{idImage}")
    public Long updatePictureAuto(@RequestParam(name = "imageFile", required = false) MultipartFile file,
                                  @PathVariable("idImage") String idImage) {
        if (file != null && idImage != null) {
            try {
                return editPictureService.editPictureAuto(file, new Long(idImage));
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return new Long(idImage);
        }
    }
}
