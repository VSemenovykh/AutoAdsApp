package ru.ncedu.implement;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.ncedu.entity.PictureAuto;
import ru.ncedu.repository.PictureAutoRepository;
import ru.ncedu.service.UpdatePictureService;
import java.io.IOException;

import static java.util.Objects.isNull;

@Slf4j
@Service
@RequiredArgsConstructor
public class UpdatePictureServiceImp implements UpdatePictureService {

    private final PictureAutoRepository pictureAutoRepository;

    @Override
    public Long updatePictureAuto(MultipartFile file, Long id) throws IOException {
        log.info("UpdatePictureServiceImp -> updatePictureAuto()");
        log.info("UpdatePictureServiceImp -> MultipartFile -> isNull: " + isNull(file));
        log.info("UpdatePictureServiceImp -> id" + id);
        PictureAuto newPictureAuto = pictureAutoRepository.findById(id).orElse(null);
        log.info("UpdatePictureServiceImp -> PictureAuto -> isNull" + isNull(newPictureAuto));
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
