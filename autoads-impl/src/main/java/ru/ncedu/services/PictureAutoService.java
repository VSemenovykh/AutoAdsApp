package ru.ncedu.services;

import ru.ncedu.entity.PictureAuto;

public interface PictureAutoService {

    PictureAuto getPictureAutoByNameImage(String nameImage);

    PictureAuto findPictureAutoById(Long id);
}
