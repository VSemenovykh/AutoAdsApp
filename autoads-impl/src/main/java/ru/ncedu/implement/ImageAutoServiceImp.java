package ru.ncedu.implement;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.ncedu.entity.ImageAuto;
import ru.ncedu.repository.ImageAutoRepository;
import ru.ncedu.service.ImageAutoService;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImageAutoServiceImp implements ImageAutoService {

    private final ImageAutoRepository imageAutoRepository;

    @Override
    public ImageAuto getImageAutoByNameImage(String nameImage) {
        ImageAuto imageAuto = imageAutoRepository.findByNameImage(nameImage);
        if (imageAuto != null) {
            ImageAuto newImageAuto = new ImageAuto(imageAuto.getId()
                    , imageAuto.getNameImage()
                    , (imageAuto.getRaster()));
            return newImageAuto;
        } else {
            return null;
        }
    }

    @Override
    public ImageAuto findImageAutoById(Long id) {
        ImageAuto imageAuto = imageAutoRepository.findImageAutoById(id);
        if (imageAuto != null) {
            ImageAuto newImageAuto = new ImageAuto(imageAuto.getId()
                    , imageAuto.getNameImage()
                    , (imageAuto.getRaster()));
            return newImageAuto;
        } else {
            return null;
        }
    }
}
