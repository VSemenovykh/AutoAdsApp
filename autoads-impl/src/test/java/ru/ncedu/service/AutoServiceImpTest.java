package ru.ncedu.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import ru.ncedu.entity.Auto;
import ru.ncedu.implement.AutoServiceImp;
import ru.ncedu.model.BodyStyle;
import ru.ncedu.model.Color;
import ru.ncedu.model.Drive;
import ru.ncedu.model.Transmission;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class AutoServiceImpTest {

    @MockBean
    AutoServiceImp autoServiceImp;

    @Test
    public void testFindById() throws Exception{
        Auto auto = new Auto(1L, 1L, 1L, 1L, 4L, Color.GRAY.name(), 1935000, Transmission.DSG.name(), Drive.FWD.name(), BodyStyle.SEDAN.name().replace("_", " "));
        Auto testAuto = autoServiceImp.findById(1L);
        assert testAuto != null;
        assertThat(auto.getId()).isEqualTo(testAuto.getId());
        assertThat(auto.getIdImage()).isEqualTo(testAuto.getIdImage());
        assertThat(auto.getIdBrand()).isEqualTo(testAuto.getIdBrand());
        assertThat(auto.getIdMotor()).isEqualTo(testAuto.getIdMotor());
        assertThat(auto.getIdContact()).isEqualTo(testAuto.getIdContact());
        assertThat(auto.getColor()).isEqualTo(testAuto.getColor());
        assertThat(auto.getPrice()).isEqualTo(testAuto.getPrice());
        assertThat(auto.getTransmissionType()).isEqualTo(testAuto.getTransmissionType());
        assertThat(auto.getDriveType()).isEqualTo(testAuto.getDriveType());
        assertThat(auto.getBodyStyleType()).isEqualTo(testAuto.getBodyStyleType());
    }
}
