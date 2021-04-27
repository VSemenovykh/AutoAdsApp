package ru.ncedu.implement;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.ncedu.model.*;
import ru.ncedu.services.CreateAutoService;

import java.io.IOException;
import java.io.RandomAccessFile;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateAutoServiceImpTest {

    @Mock
    CreateAutoService createAutoService;

    @Test
    void testSaveAuto() {
        DataAuto dataAuto = new DataAuto();
        dataAuto.setId(1L);
        dataAuto.setIdPicture(1L);
        dataAuto.setRaster(convertImageAuto("src/test/resources/image-auto/Audi/AUDI-A3.JPG"));
        dataAuto.setEmail("audi@gmail.com");
        dataAuto.setPhone("+7(111)-111-11-11");
        dataAuto.setNameBrand("AUDi");
        dataAuto.setNameModel("A3");
        dataAuto.setYear("2016");
        dataAuto.setPrice(1935000.0);
        dataAuto.setColor(Color.GRAY.name());
        dataAuto.setMotorType(Fuel.DIESEL.name());
        dataAuto.setVolume(2.0);
        dataAuto.setDriveType(Drive.FWD.name());
        dataAuto.setTransmissionType(Transmission.DSG.name());
        dataAuto.setBodyStyleType(BodyStyle.SEDAN.name().replace("_", " "));

        when(createAutoService.saveAuto(any(DataAuto.class), eq(1L))).thenReturn(dataAuto);

        DataAuto resDataAuto = createAutoService.saveAuto(dataAuto, 1L);
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
