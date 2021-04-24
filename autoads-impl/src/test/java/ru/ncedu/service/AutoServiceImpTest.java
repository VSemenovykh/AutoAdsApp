package ru.ncedu.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import ru.ncedu.AutoAdsApplication;
import ru.ncedu.entity.Auto;
import ru.ncedu.model.BodyStyle;
import ru.ncedu.model.Color;
import ru.ncedu.model.Drive;
import ru.ncedu.model.Transmission;
import ru.ncedu.repository.AutoRepository;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@RequiredArgsConstructor
//@RunWith(SpringJUnit4ClassRunner.class)
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AutoAdsApplication.class)
public class AutoServiceImpTest {

//    @MockBean
    private final AutoRepository autorepository;

//    public AutoServiceImpTest(){
//    }
//    public AutoServiceImpTest(AutoRepository autorepository){
//        this.autorepository = autorepository;
//    }

    @Test
    public void testFindById(){
        Auto autoOne = autorepository.findById(1L).orElse(null);
        assert autoOne != null;
        assertThat(autoOne.getId()).isEqualTo(1L);
        assertThat(autoOne.getIdImage()).isEqualTo(1L);
        assertThat(autoOne.getIdBrand()).isEqualTo(1L);
        assertThat(autoOne.getIdMotor()).isEqualTo(4L);
        assertThat(autoOne.getIdContact()).isEqualTo(1L);
        assertThat(autoOne.getColor()).isEqualTo(Color.GRAY.name());
        assertThat(autoOne.getPrice()).isEqualTo(1935000);
        assertThat(autoOne.getTransmissionType()).isEqualTo(Transmission.DSG.name());
        assertThat(autoOne.getDriveType()).isEqualTo(Drive.FWD.name());
        assertThat(autoOne.getBodyStyleType()).isEqualTo(BodyStyle.SEDAN.name().replace("_", " "));

//        Auto autoTwo = autoService.findById(4L);
//        assert autoTwo != null;
//        assertThat(autoTwo.getId()).isEqualTo(4L);
//        assertThat(autoTwo.getIdImage()).isEqualTo(4L);
//        assertThat(autoTwo.getIdBrand()).isEqualTo(4L);
//        assertThat(autoTwo.getIdMotor()).isEqualTo(3L);
//        assertThat(autoTwo.getIdContact()).isEqualTo(2L);
//        assertThat(autoTwo.getColor()).isEqualTo(Color.RED.name());
//        assertThat(autoTwo.getPrice()).isEqualTo(726000);
//        assertThat(autoTwo.getTransmissionType()).isEqualTo(Transmission.MANUAL.name());
//        assertThat(autoTwo.getDriveType()).isEqualTo(Drive.FWD.name());
//        assertThat(autoTwo.getBodyStyleType()).isEqualTo(BodyStyle.SEDAN.name().replace("_", " "));
//
//        Auto autoThree = autoService.findById(16L);
//        assert autoThree != null;
//        assertThat(autoThree.getId()).isEqualTo(16L);
//        assertThat(autoThree.getIdImage()).isEqualTo(16L);
//        assertThat(autoThree.getIdBrand()).isEqualTo(16L);
//        assertThat(autoThree.getIdMotor()).isEqualTo(3L);
//        assertThat(autoThree.getIdContact()).isEqualTo(6L);
//        assertThat(autoThree.getColor()).isEqualTo(Color.RED.name());
//        assertThat(autoThree.getPrice()).isEqualTo(18000000);
//        assertThat(autoThree.getTransmissionType()).isEqualTo(Transmission.AUTOMATIC.name());
//        assertThat(autoThree.getDriveType()).isEqualTo(Drive.AWD.name());
//        assertThat(autoThree.getBodyStyleType()).isEqualTo(BodyStyle.COUPE.name().replace("_", " "));
    }
}
