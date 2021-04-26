package ru.ncedu.implement;

import org.springframework.stereotype.Component;
import ru.ncedu.entity.PictureAuto;
import ru.ncedu.exceptions.NonExistingPictureAutoException;
import ru.ncedu.interfaces.PictureAutoRepositoryTest;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

@Component
public class PictureAutoRepositoryTestImp implements PictureAutoRepositoryTest {

    private List<PictureAuto> pictureAutoList;

    public PictureAutoRepositoryTestImp() {
        pictureAutoList = new ArrayList<>();
        pictureAutoList.add(new PictureAuto(1L, "A3", convertImageAuto("src/test/java/ru/ncedu/imageTest/AUDI-A3.JPG")));
        pictureAutoList.add(new PictureAuto(4L, "FIESTA", convertImageAuto("src/test/java/ru/ncedu/imageTest/FORD-FIESTA.JPG")));
        pictureAutoList.add(new PictureAuto(7L, "ACCORD", convertImageAuto("src/test/java/ru/ncedu/imageTest/HONDA-ACCORD.JPG")));
        pictureAutoList.add(new PictureAuto(10L, "SOLARIS", convertImageAuto("src/test/java/ru/ncedu/imageTest/HYUNDAI-SOLARIS.JPG")));
        pictureAutoList.add(new PictureAuto(11L, "ELANTRA", convertImageAuto("src/test/java/ru/ncedu/imageTest/HYUNDAI-ELANTRA.JPG")));
    }

    public byte[] convertImageAuto(String nameFile) {
        try (RandomAccessFile f = new RandomAccessFile(nameFile, "r")) {
            byte[] bytes = new byte[(int) f.length()];
            f.read(bytes);
            return bytes;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public PictureAuto findPictureAutoById(Long id) {
        for (PictureAuto pictureAuto : this.pictureAutoList) {
            if (pictureAuto.getId().equals(id)) {
                return pictureAuto;
            }
        }
        throw new NonExistingPictureAutoException();
    }

    @Override
    public void savePictureAuto(PictureAuto pictureAuto) {
        this.pictureAutoList.add(pictureAuto);
    }

    @Override
    public List<PictureAuto> findAll() {
        return (!this.pictureAutoList.isEmpty()) ? this.pictureAutoList : null;
    }

    @Override
    public void delete(Long id) {
        if (!isNull(findPictureAutoById(id))) {
            this.pictureAutoList.remove(findPictureAutoById(id));
        } else {
            throw new NonExistingPictureAutoException();
        }
    }
}
