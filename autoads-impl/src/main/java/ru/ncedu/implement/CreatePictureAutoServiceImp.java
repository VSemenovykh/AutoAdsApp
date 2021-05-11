package ru.ncedu.implement;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.ncedu.entity.PictureAuto;
import ru.ncedu.repositories.PictureAutoRepository;
import ru.ncedu.services.CreatePictureAutoService;
import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreatePictureAutoServiceImp implements CreatePictureAutoService {

    final private PictureAutoRepository pictureAutoRepository;

    public Long createPictureAuto(MultipartFile file) throws IOException {
        PictureAuto newPictureAuto = new PictureAuto();

        byte[] fileBytes = file.getBytes();
        String fileName = file.getOriginalFilename();

        assert fileName != null;
        if(fileName.toLowerCase().contains(".jpg") || fileName.toLowerCase().contains(".png")){
            newPictureAuto.setNameImage(fileName.toLowerCase().replaceAll("([.jpg]|[.png])", ""));
            newPictureAuto.setRaster((fileBytes));
            return pictureAutoRepository.save(newPictureAuto).getId();
        }else{
            return null;
        }
    }
}
