package ru.ncedu.implement;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.ncedu.model.*;
import ru.ncedu.services.AutoService;
import ru.ncedu.services.EditAutoService;
import java.io.IOException;
import java.io.RandomAccessFile;

@ExtendWith(MockitoExtension.class)
class EditAutoServiceImpTest {

    @Mock
    EditAutoService editAutoService;

    @Mock
    AutoService autoService;

    @Test
    void testEditAuto() {
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

        DataAuto beforeDataAuto = autoService.findAutoJoinById(1L);
        editAutoService.editAuto(dataAuto, 1L, 1L);
        DataAuto afterDataAuto = autoService.findAutoJoinById(1L);
        assert beforeDataAuto != afterDataAuto;
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
