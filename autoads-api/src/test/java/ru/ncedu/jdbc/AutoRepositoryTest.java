package ru.ncedu.jdbc;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.ncedu.entity.Auto;
import ru.ncedu.model.*;
import ru.ncedu.repository.AutoRepository;

import static org.assertj.core.api.Assertions.assertThat;

//????????????
//@ExtendWith(SpringExtension.class)
//@AutoConfigureJsonTesters
//@WebMvcTest(AutoRepositoryTestImp.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AutoRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AutoRepository autoRepository;

//    @MockBean
//    private ru.ncedu.repository.AutoRepositoryTest autoRepositoryTest;

    @Test
    public void testAuto(){
        Auto testAuto = new Auto();
        testAuto.setId(1L);
        testAuto.setIdImage(1L);
        testAuto.setIdBrand(1L);
        testAuto.setIdContact(1L);
        testAuto.setIdMotor(4L);
        testAuto.setColor(Color.GRAY.name());
        testAuto.setPrice(1935000);
        testAuto.setTransmissionType(Transmission.DSG.name());
        testAuto.setDriveType(Drive.FWD.name());
        testAuto.setBodyStyleType(BodyStyle.SEDAN.name().replace("_", " "));

        this.entityManager.persist(testAuto);
        Auto auto = this.autoRepository.getOne(1L);
        assertThat(auto.getIdImage()).isEqualTo(1L);
        assertThat(auto.getIdBrand()).isEqualTo(1L);
        assertThat(auto.getIdMotor()).isEqualTo(4L);
        assertThat(auto.getIdContact()).isEqualTo(1L);
        assertThat(auto.getPrice()).isEqualTo(1935000);
        assertThat(auto.getColor()).isEqualTo(Color.GRAY.name());
        assertThat(auto.getDriveType()).isEqualTo(Drive.FWD.name());
        assertThat(auto.getTransmissionType()).isEqualTo(Transmission.DSG.name());
        assertThat(auto.getBodyStyleType()).isEqualTo(BodyStyle.SEDAN.name());
//        given(autoRepositoryTestImp.getAutoById(1L))
//                .willReturn(new Auto(1L, 1L, 1L, 1L, 4L, Color.GRAY.name(), 1935000, Transmission.DSG.name(), Drive.FWD.name(), BodyStyle.SEDAN.name().replace("_", " ")));
//        Auto testAuto = autoRepositoryTest.getAutoById(1L);
//        assertThat(testAuto).isEqualTo(new Auto(1L, 1L, 1L, 1L, 4L, Color.GRAY.name(), 1935000, Transmission.DSG.name(), Drive.FWD.name(), BodyStyle.SEDAN.name().replace("_", " ")));
    }
}
