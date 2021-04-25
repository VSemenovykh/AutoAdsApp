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
import ru.ncedu.controller.CreateAutoController;
import ru.ncedu.entity.Auto;
import ru.ncedu.entity.Brand;
import ru.ncedu.entity.Contact;
import ru.ncedu.entity.Motor;
import ru.ncedu.model.*;
import ru.ncedu.repository.AutoRepository;
import ru.ncedu.repository.BrandRepository;
import ru.ncedu.repository.ContactRepository;
import ru.ncedu.repository.MotorRepository;
import java.io.IOException;
import java.io.RandomAccessFile;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//???????????
@Slf4j
@RunWith(MockitoJUnitRunner.class)
@AutoConfigureMockMvc
@WebMvcTest(CreateAutoController.class)
public class CreateAutoControllerTest {

    @Autowired
    private MockMvc mvc;

    @Mock
    private AutoRepository autoRepository;

    @Mock
    private BrandRepository brandRepository;

    @Mock
    private MotorRepository motorRepository;

    @Mock
    private ContactRepository contactRepository;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void testCreateAuto() throws Exception {
        byte[] raster = new byte[0];
       // String nameFile = "./image-auto/Audi/AUDI-A3.JPG";
        String nameFile = "C:/IdeaProjects/ProjectByNetcracker/Backend-AutoAdsApp/image-auto/Audi/AUDI-A3.JPG";

        try (RandomAccessFile f = new RandomAccessFile(nameFile, "r")) {
            byte[] bytes = new byte[(int) f.length()];
            f.read(bytes);

        } catch (IOException e) {
            e.printStackTrace();
        }

        Brand brand = new Brand(1L,"AUDI", "A3", "2016");
        Motor motor = new Motor(4L, Fuel.DIESEL.name(), 2.0);
        Contact contact = new Contact(1L, "audi@gmail.com", "+7(111)-111-11-11");
        Auto auto = new Auto(1L, 1L, brand.getId(), contact.getId(), motor.getId(), Color.GRAY.name(), 1935000, Transmission.DSG.name(), Drive.FWD.name(), BodyStyle.SEDAN.name().replace("_", " "));
        DataAuto dataAuto = new DataAuto(1L, auto.getIdImage(),  raster, contact.getEmail(),contact.getPhone(), brand.getNameBrand(), brand.getNameModel(),brand.getYear(),auto.getColor(),auto.getPrice(),motor.getMotorType(), motor.getVolume(),auto.getDriveType(), auto.getTransmissionType(), auto.getBodyStyleType());

        Mockito.when(brandRepository.save(brand)).thenReturn(brand);
        Mockito.when(motorRepository.save(motor)).thenReturn(motor);
        Mockito.when(contactRepository.save(contact)).thenReturn(contact);
        Mockito.when(autoRepository.save(auto)).thenReturn(auto);

        String url = "/api/all/add";

        MvcResult mvcResult = (MvcResult) mvc.perform(post(url)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(dataAuto))
                 ).andExpect(status().isOk());

        String actualJsonResponse = mvcResult.getResponse().getContentAsString();

        log.info("actualJsonResponse: " + actualJsonResponse);

        String expectedJsonResponse = objectMapper.writeValueAsString(dataAuto);

    }

}
