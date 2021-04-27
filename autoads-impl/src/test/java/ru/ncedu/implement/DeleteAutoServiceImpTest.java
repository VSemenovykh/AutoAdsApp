package ru.ncedu.implement;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.ncedu.entity.Auto;
import ru.ncedu.model.BodyStyle;
import ru.ncedu.model.Color;
import ru.ncedu.model.Drive;
import ru.ncedu.model.Transmission;
import ru.ncedu.services.AutoService;
import ru.ncedu.services.DeleteAutoService;
import static java.util.Objects.isNull;

@ExtendWith(MockitoExtension.class)
class DeleteAutoServiceImpTest {

    @Mock
    DeleteAutoService deleteAutoService;

    @Mock
    AutoService autoService;

    @Test
    void testDeleteAuto() {
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

        deleteAutoService.deleteAuto(1L);
        Auto resAuto = autoService.findById(1L);
        assert isNull(resAuto);
    }
}
