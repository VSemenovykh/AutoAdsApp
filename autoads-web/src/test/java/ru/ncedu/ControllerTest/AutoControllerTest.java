package ru.ncedu.ControllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import ru.ncedu.controller.AutoController;
import ru.ncedu.entity.Auto;
import ru.ncedu.model.*;
import ru.ncedu.interfaces.AutoRepository;
import java.io.IOException;
import java.io.RandomAccessFile;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//????????????
@Slf4j
@RunWith(MockitoJUnitRunner.class)
@AutoConfigureMockMvc
@WebMvcTest(AutoController.class)
public class AutoControllerTest {

    @Autowired
    private MockMvc mvc;

    @Mock
    private AutoRepository autoRepository;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void testGetAutoById() throws Exception {
        Auto auto = new Auto();
        auto.setId(1L);
        auto.setIdImage(1L);
        auto.setIdBrand(1L);
        auto.setIdContact(1L);
        auto.setIdMotor(4L);
        auto.setColor(Color.GRAY.name());
        auto.setPrice(1935000);
        auto.setTransmissionType(Transmission.DSG.name());
        auto.setDriveType(Drive.FWD.name());
        auto.setBodyStyleType(BodyStyle.SEDAN.name().replace("_", " "));
        log.info("Auto: " + auto);

        Mockito.when(autoRepository.getAutoById(1L)).thenReturn(auto);

        String url = "/api/all/1";
        MvcResult mvcResult = mvc.perform(get(url)).andExpect(status().isOk()).andReturn();

        String actualJsonResponse = mvcResult.getResponse().getContentAsString();
        log.info("actualJsonResponse: " + actualJsonResponse);

        String expectedJsonResponse = objectMapper.writeValueAsString(auto);

        assertThat(actualJsonResponse).isEqualToIgnoringWhitespace(expectedJsonResponse);
    }

    @Test
    public void testGetAutoJoinById() throws Exception {
        Auto auto = new Auto();
        auto.setId(1L);
        auto.setIdImage(1L);
        auto.setIdBrand(1L);
        auto.setIdContact(1L);
        auto.setIdMotor(4L);
        auto.setColor(Color.GRAY.name());
        auto.setPrice(1935000);
        auto.setTransmissionType(Transmission.DSG.name());
        auto.setDriveType(Drive.FWD.name());
        auto.setBodyStyleType(BodyStyle.SEDAN.name().replace("_", " "));

        DataAuto dataAuto = new DataAuto();
        dataAuto.setId(1L);
        dataAuto.setIdPicture(1L);
        dataAuto.setRaster( convertImageAuto("./image-auto/Audi/AUDI-A3.JPG"));
        dataAuto.setEmail("audi@gmail.com");
        dataAuto.setPhone("+7(111)-111-11-11");
        dataAuto.setNameBrand("AUDI");
        dataAuto.setNameModel("A3");
        dataAuto.setYear("2016");
        dataAuto.setPrice(1935000.0);
        dataAuto.setColor(Color.GRAY.name());
        dataAuto.setMotorType(Fuel.DIESEL.name());
        dataAuto.setVolume(2.0);
        dataAuto.setDriveType(Drive.FWD.name());
        dataAuto.setTransmissionType(Transmission.DSG.name());
        dataAuto.setBodyStyleType(BodyStyle.SEDAN.name().replace("_", " "));
        log.info("dataAuto: " + dataAuto);

        Mockito.when(autoRepository.getAutoById(1L)).thenReturn(auto);

        String url = "/api/all/join/1";
        MvcResult mvcResult = mvc.perform(get(url)).andExpect(status().isOk()).andReturn();

        String actualJsonResponse = mvcResult.getResponse().getContentAsString();
        log.info("actualJsonResponse: " + actualJsonResponse);

        String expectedJsonResponse = objectMapper.writeValueAsString(dataAuto);

        assertThat(actualJsonResponse).isEqualToIgnoringWhitespace(expectedJsonResponse);
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
