package ru.ncedu.implement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.ncedu.entity.PictureAuto;
import ru.ncedu.repository.PictureAutoRepository;
import ru.ncedu.service.UpdatePictureService;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UpdatePictureServiceImp implements UpdatePictureService {

    private final PictureAutoRepository pictureAutoRepository;

    @Override
    public Long updatePictureAuto(MultipartFile file, Long id ) throws IOException {
        Optional<PictureAuto> newPictureAuto = Optional.ofNullable(pictureAutoRepository.findById(id).orElse(null));
        byte[] fileBytes = file.getBytes();
        String fileName = file.getOriginalFilename();

        newPictureAuto.get().setNameImage(fileName.replaceAll(".jpg", ""));
        newPictureAuto.get().setRaster((fileBytes));

        return pictureAutoRepository.save(newPictureAuto.get()).getId();
    }
}
