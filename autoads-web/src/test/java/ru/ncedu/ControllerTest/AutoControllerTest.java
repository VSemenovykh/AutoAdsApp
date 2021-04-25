package ru.ncedu.ControllerTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import ru.ncedu.controller.AutoController;
import ru.ncedu.entity.Auto;
import ru.ncedu.model.*;
import ru.ncedu.services.AutoService;
import java.io.IOException;
import java.io.RandomAccessFile;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(MockitoJUnitRunner.class)
@AutoConfigureJsonTesters
@WebMvcTest(AutoController.class)
public class AutoControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AutoService autoService;

    @Autowired
    private JacksonTester<Auto> jsonAuto;

    @Autowired
    private JacksonTester<DataAuto> jsonDataAuto;

    @Test
    public void getAutoByIdTest() throws Exception {
        // given
        given(autoService.findById(1L))
                .willReturn((new Auto(1L, 1L, 1L, 1L, 4L, Color.GRAY.name(), 1935000, Transmission.DSG.name(), Drive.FWD.name(), BodyStyle.SEDAN.name().replace("_", " "))));

        // when
        MockHttpServletResponse response = mvc.perform(
                get("/api/all/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(
                jsonAuto.write(new Auto(1L, 1L, 1L, 1L, 4L, Color.GRAY.name(), 1935000, Transmission.DSG.name(), Drive.FWD.name(), BodyStyle.SEDAN.name().replace("_", " "))).getJson());
    }

    @Test
    public void getAutoJoinByIdTest() throws Exception {
        // given
        given(autoService.findAutoJoinById(1L))
                .willReturn(new DataAuto(1L, 1L,  convertImageAuto("./image-auto/Audi/AUDI-A3.JPG"), "audi@gmail.com", "+7(111)-111-11-11", "AUDI", "A3", "2016", Color.GRAY.name(), 1935000.0, Fuel.DIESEL.name(), 2.0, Drive.FWD.name(), Transmission.DSG.name(), BodyStyle.SEDAN.name().replace("_", " ")));

        // when
        MockHttpServletResponse response = mvc.perform(
                get("/api/all/join/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(
                jsonDataAuto.write(new DataAuto(1L, 1L,  convertImageAuto("./image-auto/Audi/AUDI-A3.JPG"), "audi@gmail.com", "+7(111)-111-11-11", "AUDI", "A3", "2016", Color.GRAY.name(), 1935000.0, Fuel.DIESEL.name(), 2.0, Drive.FWD.name(), Transmission.DSG.name(), BodyStyle.SEDAN.name().replace("_", " "))).getJson());

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
