package ru.ncedu.service;

import ru.ncedu.entity.PictureAuto;

public interface PictureAutoService {

    PictureAuto getPictureAutoByNameImage(String nameImage);

    PictureAuto findPictureAutoById(Long id);
}
