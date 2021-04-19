package ru.ncedu.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.ncedu.service.CreatePictureAutoService;
import java.io.IOException;

import static java.util.Objects.isNull;
import static org.springframework.util.StringUtils.isEmpty;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/auth")
public class CreatePictureAutoController {

    private final CreatePictureAutoService createPictureAutoService;

    @PostMapping(path = "/pictureAuto")
    public Long pictureAuto(@RequestParam(name = "imageFile") MultipartFile file) {
        log.info("CreatePictureAutoController -> pictureAuto()");
        log.info("CreatePictureAutoController -> file isNull: " + isNull(file));
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
