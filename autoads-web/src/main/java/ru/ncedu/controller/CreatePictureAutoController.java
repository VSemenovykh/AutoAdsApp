package ru.ncedu.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.ncedu.service.CreatePictureAutoService;
import java.io.IOException;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/all")
public class CreatePictureAutoController {

    private final CreatePictureAutoService createPictureAutoService;

    @PostAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    @PostMapping(path = "/pictureAuto")
    public Long pictureAuto(@RequestParam(name = "imageFile") MultipartFile file) {
        if (file != null) {
            try {
                return createPictureAutoService.createPictureAuto(file);

            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }
}
