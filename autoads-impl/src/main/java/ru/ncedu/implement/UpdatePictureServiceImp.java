package ru.ncedu.implement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.ncedu.entity.PictureAuto;
import ru.ncedu.repository.PictureAutoRepository;
import ru.ncedu.service.UpdatePictureService;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class UpdatePictureServiceImp implements UpdatePictureService {

    private final PictureAutoRepository pictureAutoRepository;

    @Override
    public Long updatePictureAuto(MultipartFile file, Long id) throws IOException {
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
