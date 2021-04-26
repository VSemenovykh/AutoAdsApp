package ru.ncedu.jdbc;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.ncedu.entity.Auto;
import ru.ncedu.implement.AutoRepositoryTestImp;
import ru.ncedu.model.*;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class AutoRepositoryTest {

    @Mock
    private AutoRepositoryTestImp autoRepositoryTestImp;

    //?????
    @BeforeEach
    public void setUp() {
        autoRepositoryTestImp = new AutoRepositoryTestImp();
    }

    @Test
    public void testAuto(){
        autoRepositoryTestImp = new AutoRepositoryTestImp();
        Auto testAuto = autoRepositoryTestImp.getAutoById(1L);
        assertThat(testAuto).isEqualTo(new Auto(1L, 1L, 1L, 1L, 4L, Color.GRAY.name(), 1935000, Transmission.DSG.name(), Drive.FWD.name(), BodyStyle.SEDAN.name().replace("_", " ")));
    }
}
