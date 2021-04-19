package ru.ncedu.implement;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.ncedu.entity.PictureAuto;
import ru.ncedu.repository.PictureAutoRepository;
import ru.ncedu.service.PictureAutoService;

import static java.util.Objects.isNull;


@Slf4j
@Service
@RequiredArgsConstructor
public class PictureAutoServiceImp implements PictureAutoService {

    private final PictureAutoRepository pictureAutoRepository;

    @Override
    public PictureAuto getPictureAutoByNameImage(String namePicture) {
        log.info("PictureAutoServiceImp -> getPictureAutoByNameImage()");
        log.info("PictureAutoServiceImp -> namePicture: " + namePicture);
        PictureAuto pictureAuto = pictureAutoRepository.findByNameImage(namePicture);
        log.info("PictureAutoServiceImp -> PictureAuto -> isNull: " + isNull(pictureAuto));
        return (pictureAuto != null) ? (new PictureAuto(pictureAuto.getId(), pictureAuto.getNameImage(), (pictureAuto.getRaster()))) : null;
    }

    @Override
    public PictureAuto findPictureAutoById(Long id) {
        log.info("PictureAutoServiceImp -> findPictureAutoById()");
        log.info("PictureAutoServiceImp -> id: " + id);
        PictureAuto pictureAuto = pictureAutoRepository.findImageAutoById(id);
        log.info("PictureAutoServiceImp -> PictureAuto -> isNull: " + isNull(pictureAuto));
        return (pictureAutoRepository.findImageAutoById(id) != null) ? (new PictureAuto(pictureAuto.getId(), pictureAuto.getNameImage(), (pictureAuto.getRaster()))) : null;
    }
}
