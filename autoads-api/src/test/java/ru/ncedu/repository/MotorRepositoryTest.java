package ru.ncedu.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.*;
import ru.ncedu.entity.Motor;
import ru.ncedu.implement.MotorRepositoryTestImp;
import ru.ncedu.model.Fuel;

import java.util.List;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class MotorRepositoryTest {

//  @Mock
private MotorRepositoryTestImp motorRepositoryTest;

    //?????
//    @BeforeEach
//    public void setUp() {
//        motorRepositoryTest = new MotorRepositoryTestImp();
//    }

    @Test
    public void testFindBrandById() {
        motorRepositoryTest = new MotorRepositoryTestImp();
        Motor testMotor = motorRepositoryTest.getMotorById(1L);
        assertThat(testMotor).isEqualTo(new Motor(1L, Fuel.DIESEL.name(), 1.0));
    }

    @Test
    public void testSaveBrand() {
        motorRepositoryTest = new MotorRepositoryTestImp();
        motorRepositoryTest.saveMotor(new Motor(10L, Fuel.DIESEL.name(), 5.0));
        Motor testMotor = motorRepositoryTest.getMotorById(10L);
        assertThat(testMotor).isEqualTo(new Motor(10L, Fuel.DIESEL.name(), 5.0));
    }

    @Test
    public void testFindAll() {
        motorRepositoryTest = new MotorRepositoryTestImp();
        List<Motor> motorList = motorRepositoryTest.findAll();
        assertThat(motorList).isNotNull();
        log.info("Added motor successfully");
    }

    @Test
    public void testDelete() {
        motorRepositoryTest = new MotorRepositoryTestImp();
        testSaveBrand();
        motorRepositoryTest.delete(10L);
        log.info("Deleted motor from list");
    }
}
