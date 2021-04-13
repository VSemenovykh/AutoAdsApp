package ru.ncedu.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.ncedu.service.CreatePictureAutoService;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/auth")
public class CreatePictureAutoController {

    private final CreatePictureAutoService createPictureAutoService;

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
