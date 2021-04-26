package ru.ncedu.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.ncedu.entity.Auto;
import ru.ncedu.implement.AutoRepositoryTestImp;
import ru.ncedu.model.*;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class AutoRepositoryTest {

    //    @Mock
    private AutoRepositoryTestImp autoRepositoryTestImp;

    //?????
//    @BeforeEach
//    public void setUp() {
//        autoRepositoryTestImp = new AutoRepositoryTestImp();
//    }

    @Test
    public void testFindAutoById() {
        this.autoRepositoryTestImp = new AutoRepositoryTestImp();
        Auto testAuto = this.autoRepositoryTestImp.getAutoById(1L);
        assertThat(testAuto).isEqualTo(new Auto(1L, 1L, 1L, 1L, 4L, Color.GRAY.name(), 1935000, Transmission.DSG.name(), Drive.FWD.name(), BodyStyle.SEDAN.name().replace("_", " ")));
    }

    @Test
    public void testSaveAuto() {
        this.autoRepositoryTestImp = new AutoRepositoryTestImp();
        this.autoRepositoryTestImp.saveAuto(new Auto(6L, 2L, 2L, 1L, 5L, Color.BROWN.name(), 2935000, Transmission.CVT.name(), Drive.FWD.name(), BodyStyle.HATCHBACK.name().replace("_", " ")));
        Auto testAuto = this.autoRepositoryTestImp.getAutoById(6L);
        assertThat(testAuto).isEqualTo(new Auto(6L, 2L, 2L, 1L, 5L, Color.BROWN.name(), 2935000, Transmission.CVT.name(), Drive.FWD.name(), BodyStyle.HATCHBACK.name().replace("_", " ")));
    }

    @Test
    public void testFindAll() {
        this.autoRepositoryTestImp = new AutoRepositoryTestImp();
        List<Auto> autoList = this.autoRepositoryTestImp.findAll();
        log.info("Added auto successfully");
        assertThat(autoList).isNotNull();
    }

    @Test
    public void testDelete() {
        this.autoRepositoryTestImp = new AutoRepositoryTestImp();
        testSaveAuto();
        this.autoRepositoryTestImp.delete(6L);
        log.info("Deleted auto from list");
    }
}
