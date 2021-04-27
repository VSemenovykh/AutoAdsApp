package ru.ncedu.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import java.io.IOException;
import java.io.RandomAccessFile;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import ru.ncedu.model.*;
import ru.ncedu.services.CreateAutoService;

@ExtendWith(MockitoExtension.class)
class CreateAutoControllerTest {

    @InjectMocks
    CreateAutoController createAutoController;

    @Mock
    CreateAutoService createAutoService;

    @Test
    public void testCreateAuto(){
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

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

        when(createAutoService.saveAuto(any(DataAuto.class),eq(1L))).thenReturn(dataAuto);

        ResponseEntity<DataAuto> responseEntity = createAutoController.createAuto(dataAuto, "1");
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
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
