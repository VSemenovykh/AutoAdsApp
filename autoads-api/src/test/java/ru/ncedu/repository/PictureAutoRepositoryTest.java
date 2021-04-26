package ru.ncedu.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.ncedu.entity.PictureAuto;
import ru.ncedu.implement.PictureAutoRepositoryTestImp;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class PictureAutoRepositoryTest {

    private PictureAutoRepositoryTestImp pictureAutoRepositoryTestImp;

    @Test
    public void testFindBrandById() {
        pictureAutoRepositoryTestImp = new PictureAutoRepositoryTestImp();
        PictureAuto testPictureAuto = pictureAutoRepositoryTestImp.findPictureAutoById(1L);
        assertThat(testPictureAuto).isEqualTo(new PictureAuto(1L, "A3", pictureAutoRepositoryTestImp.convertImageAuto("src/test/java/ru/ncedu/imageTest/AUDI-A3.JPG")));
    }

    @Test
    public void testSavePictureAuto() {
        pictureAutoRepositoryTestImp = new PictureAutoRepositoryTestImp();
        pictureAutoRepositoryTestImp.savePictureAuto(new PictureAuto(21L, "RIO X", pictureAutoRepositoryTestImp.convertImageAuto("src/test/java/ru/ncedu/imageTest/Kia-Rio-X.JPG")));
        PictureAuto testPictureAutor = pictureAutoRepositoryTestImp.findPictureAutoById(21L);
        assertThat(testPictureAutor).isEqualTo(new PictureAuto(21L, "RIO X", pictureAutoRepositoryTestImp.convertImageAuto("src/test/java/ru/ncedu/imageTest/Kia-Rio-X.JPG")));
    }

    @Test
    public void testFindAll() {
        pictureAutoRepositoryTestImp = new PictureAutoRepositoryTestImp();
        List<PictureAuto> pictureAutoList = pictureAutoRepositoryTestImp.findAll();
        assertThat(pictureAutoList).isNotNull();
        log.info("Added picture auto successfully");
    }

    @Test
    public void testDelete() {
        pictureAutoRepositoryTestImp = new PictureAutoRepositoryTestImp();
        testSavePictureAuto();
        pictureAutoRepositoryTestImp.delete(21L);
        log.info("Deleted picture auto from list");
    }
}
