package ru.ncedu.service;

import ru.ncedu.entity.ImageAuto;

public interface ImageAutoService {

    ImageAuto getImageAutoByNameImage(String nameImage);

    ImageAuto findImageAutoById(Long id);
}
