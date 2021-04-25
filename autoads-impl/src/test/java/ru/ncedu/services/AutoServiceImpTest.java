package ru.ncedu.services;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import ru.ncedu.entity.Auto;
import ru.ncedu.entity.Brand;
import ru.ncedu.entity.Contact;
import ru.ncedu.entity.Motor;
import ru.ncedu.model.BodyStyle;
import ru.ncedu.model.Color;
import ru.ncedu.model.Drive;
import ru.ncedu.model.Transmission;
import ru.ncedu.repository.AutoRepository;
import ru.ncedu.repository.BrandRepository;
import ru.ncedu.repository.ContactRepository;
import ru.ncedu.repository.MotorRepository;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;

//???????????
@Slf4j
@RunWith(MockitoJUnitRunner.class)
@AutoConfigureMockMvc
public class AutoServiceImpTest {

    @Mock
    AutoRepository autoRepository;

    @Mock
    BrandRepository brandRepository;

    @Mock
    MotorRepository motorRepository;

    @Mock
    ContactRepository contactRepository;

    @Test
    public void testFindBrandById() throws Exception{
        Brand testBrand = brandRepository.findById(1L).orElse(null);
        assertNotNull(testBrand);
    }

    @Test
    public void testFindMotorById() throws Exception{
        Motor testMotor = motorRepository.findById(1L).orElse(null);
        assertNotNull(testMotor);
    }

    @Test
    public void testFindContactById() throws Exception{
        Contact testContact = contactRepository.findById(1L).orElse(null);
        assertNotNull(testContact);
    }

    @Test
    public void testFindAutoById() throws Exception{
        Auto auto = new Auto(1L, 1L, 1L, 1L, 4L, Color.GRAY.name(), 1935000, Transmission.DSG.name(), Drive.FWD.name(), BodyStyle.SEDAN.name().replace("_", " "));
        Auto testAuto = autoRepository.findById(2L).orElse(null);
        assertNotNull(testAuto);
    }
}
