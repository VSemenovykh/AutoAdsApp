package ru.ncedu.implement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.ncedu.entity.PictureAuto;
import ru.ncedu.repository.PictureAutoRepository;
import ru.ncedu.service.CreatePictureAutoService;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class CreatePictureAutoServiceImp implements CreatePictureAutoService {

    final private PictureAutoRepository pictureAutoRepository;

    public Long createPictureAuto(MultipartFile file) throws IOException {
        PictureAuto newPictureAuto = new PictureAuto();

        byte[] fileBytes = file.getBytes();
        String fileName = file.getOriginalFilename();

        newPictureAuto.setNameImage(fileName.replaceAll("([.jpg]+[.png])", ""));
        newPictureAuto.setRaster((fileBytes));

        return pictureAutoRepository.save(newPictureAuto).getId();
    }
}
