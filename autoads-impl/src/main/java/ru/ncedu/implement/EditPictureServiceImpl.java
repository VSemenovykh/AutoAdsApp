package ru.ncedu.implement;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.ncedu.entity.PictureAuto;
import ru.ncedu.repositories.PictureAutoRepository;
import ru.ncedu.services.EditPictureService;
import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
public class EditPictureServiceImpl implements EditPictureService {

    private final PictureAutoRepository pictureAutoRepository;

    @Override
    public Long editPictureAuto(MultipartFile file, Long id) throws IOException {
        PictureAuto newPictureAuto = pictureAutoRepository.findById(id).orElse(null);
        if (newPictureAuto != null) {
            byte[] fileBytes = file.getBytes();
            String fileName = file.getOriginalFilename();

            newPictureAuto.setNameImage(fileName.replaceAll(".jpg", ""));
            newPictureAuto.setRaster((fileBytes));

            return pictureAutoRepository.save(newPictureAuto).getId();
        } else {
            return null;
        }
    }
}
