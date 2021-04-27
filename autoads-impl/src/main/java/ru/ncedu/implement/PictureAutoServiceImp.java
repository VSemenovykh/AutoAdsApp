package ru.ncedu.implement;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.ncedu.entity.PictureAuto;
import ru.ncedu.interfaces.PictureAutoRepository;
import ru.ncedu.services.PictureAutoService;

@Slf4j
@Service
@RequiredArgsConstructor
public class PictureAutoServiceImp implements PictureAutoService {

    private final PictureAutoRepository pictureAutoRepository;

    @Override
    public PictureAuto getPictureAutoByNameImage(String namePicture) {
        PictureAuto pictureAuto = pictureAutoRepository.findByNameImage(namePicture);
        return (pictureAuto != null) ? (new PictureAuto(pictureAuto.getId(), pictureAuto.getNameImage(), (pictureAuto.getRaster()))) : null;
    }

    @Override
    public PictureAuto findPictureAutoById(Long id) {
        PictureAuto pictureAuto = pictureAutoRepository.findImageAutoById(id);
        return (pictureAutoRepository.findImageAutoById(id) != null) ? (new PictureAuto(pictureAuto.getId(), pictureAuto.getNameImage(), (pictureAuto.getRaster()))) : null;
    }
}
