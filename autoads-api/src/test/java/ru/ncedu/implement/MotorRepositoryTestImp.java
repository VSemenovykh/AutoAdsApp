package ru.ncedu.implement;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.ncedu.entity.Motor;
import ru.ncedu.exceptions.NonExistingAutoException;
import ru.ncedu.exceptions.NonExistingMotorException;
import ru.ncedu.model.Fuel;
import ru.ncedu.interfaces.MotorRepositoryTest;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

@Slf4j
@Component
public class MotorRepositoryTestImp implements MotorRepositoryTest {

    private List<Motor> motorList;

    public MotorRepositoryTestImp() {
        motorList = new ArrayList<>();
        motorList.add(new Motor(1L, Fuel.DIESEL.name(), 1.0));
        motorList.add(new Motor(2L, Fuel.DIESEL.name(), 1.3));
        motorList.add(new Motor(3L, Fuel.DIESEL.name(), 1.6));
        motorList.add(new Motor(4L, Fuel.DIESEL.name(), 2.0));
        motorList.add(new Motor(4L, Fuel.DIESEL.name(), 2.0));
        motorList.add(new Motor(5L, Fuel.DIESEL.name(), 2.4));
    }

    @Override
    public Motor getMotorById(Long id) {
        for (Motor motor : this.motorList) {
            if (motor.getId().equals(id)) {
                return motor;
            }
        }
        throw new NonExistingAutoException();
    }

    @Override
    public void saveMotor(Motor motor) {
        this.motorList.add(motor);
    }

    @Override
    public List<Motor> findAll() {
        return (!this.motorList.isEmpty()) ? this.motorList : null;
    }

    @Override
    public void delete(Long id) {
        if (!isNull(getMotorById(id))) {
            this.motorList.remove(getMotorById(id));
        } else {
            throw new NonExistingMotorException();
        }
    }
}
