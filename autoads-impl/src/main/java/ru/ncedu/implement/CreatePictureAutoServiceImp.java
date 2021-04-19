package ru.ncedu.implement;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.ncedu.entity.PictureAuto;
import ru.ncedu.repository.PictureAutoRepository;
import ru.ncedu.service.CreatePictureAutoService;
import java.io.IOException;

import static java.util.Objects.isNull;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreatePictureAutoServiceImp implements CreatePictureAutoService {

    final private PictureAutoRepository pictureAutoRepository;

    public Long createPictureAuto(MultipartFile file) throws IOException {
        log.info("CreatePictureAutoServiceImp -> createPictureAuto()");
        log.info("CreatePictureAutoServiceImp -> MultipartFile isNull: " + isNull(file));
        PictureAuto newPictureAuto = new PictureAuto();

        byte[] fileBytes = file.getBytes();
        String fileName = file.getOriginalFilename();

        newPictureAuto.setNameImage(fileName.toLowerCase().replaceAll("([.jpg]|[.png])", ""));
        newPictureAuto.setRaster((fileBytes));

        return pictureAutoRepository.save(newPictureAuto).getId();
    }
}
