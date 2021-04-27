package ru.ncedu.implement;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.ncedu.entity.Auto;
import ru.ncedu.model.*;
import ru.ncedu.services.AutoService;
import java.io.IOException;
import java.io.RandomAccessFile;
import static org.mockito.Mockito.when;

@Slf4j
@ExtendWith(MockitoExtension.class)
class AutoServiceImpTest {

    @Mock
    AutoService autoService;

    @Test
    void testFindById() {
        Auto auto = new Auto();
        auto.setId(1L);
        auto.setIdImage(1L);
        auto.setIdBrand(1L);
        auto.setIdContact(1L);
        auto.setIdMotor(4L);
        auto.setColor(Color.GRAY.name());
        auto.setPrice(1935000);
        auto.setDriveType(Drive.FWD.name());
        auto.setTransmissionType(Transmission.DSG.name());
        auto.setBodyStyleType(BodyStyle.SEDAN.name().replace("_", " "));

        when(autoService.findById(1L)).thenReturn(auto);

        Auto resAuto = autoService.findById(1L);
        assert resAuto != null;
        assert resAuto.equals(auto);
    }

    @Test
    void testFindAutoJoinById() {
        // given
        DataAuto dataAuto = new DataAuto(1L,
                                         1L,
                                         convertImageAuto("src/test/resources/image-auto/Audi/AUDI-A3.JPG"),
                                         "audi@gmail.com",
                                         "+7(111)-111-11-11",
                                         "AUDI",
                                         "A3",
                                         "2016",
                                          Color.GRAY.name(),
                                         1935000.0,
                                          Fuel.DIESEL.name(),
                                         2.0,
                                          Transmission.DSG.name(),
                                          Drive.FWD.name(),
                                          BodyStyle.SEDAN.name().replace("_", " "));


        when(autoService.findAutoJoinById(1L)).thenReturn(dataAuto);

        DataAuto resDataAuto = autoService.findAutoJoinById(1L);
        assert resDataAuto != null;
        assert resDataAuto.equals(dataAuto);
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
}
