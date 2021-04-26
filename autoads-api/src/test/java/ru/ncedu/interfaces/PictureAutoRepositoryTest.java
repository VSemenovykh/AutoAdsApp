package ru.ncedu.interfaces;

import ru.ncedu.entity.PictureAuto;
import java.util.List;

public interface PictureAutoRepositoryTest {

    PictureAuto findPictureAutoById(Long id);

    void savePictureAuto(PictureAuto pictureAuto);

    List<PictureAuto> findAll();

    void delete(Long id);
}
