package ru.ncedu.jdbc;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.ncedu.entity.Motor;
import ru.ncedu.model.Fuel;
import ru.ncedu.repository.MotorRepository;

import static org.assertj.core.api.Assertions.assertThat;

//???????
@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class MotorRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private MotorRepository motorRepository;

    @Test
    public void testMotor(){
        this.entityManager.persist(new Motor(1L, Fuel.DIESEL.name(),2.0));
        Motor motor = this.motorRepository.getOne(1L);
        assertThat(motor.getMotorType()).isEqualTo("DIESEL");
        assertThat(motor.getVolume()).isEqualTo(2.0);
    }
}
