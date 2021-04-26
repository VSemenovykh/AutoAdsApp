package ru.ncedu.implement;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.ncedu.exceptions.NonExistingAutoException;
import ru.ncedu.entity.Auto;
import ru.ncedu.model.BodyStyle;
import ru.ncedu.model.Color;
import ru.ncedu.model.Drive;
import ru.ncedu.model.Transmission;
import ru.ncedu.interfaces.AutoRepositoryTest;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

@Slf4j
@Component
public class AutoRepositoryTestImp implements AutoRepositoryTest {

    private List<Auto> autoList;

    public AutoRepositoryTestImp() {
        autoList = new ArrayList<>();
        autoList.add(new Auto(1L, 1L, 1L, 1L, 4L, Color.GRAY.name(), 1935000, Transmission.DSG.name(), Drive.FWD.name(), BodyStyle.SEDAN.name().replace("_", " ")));
        autoList.add(new Auto(4L, 4L, 4L, 2L, 3L, Color.RED.name(), 726000, Transmission.MANUAL.name(), Drive.FWD.name(), BodyStyle.SEDAN.name().replace("_", " ")));
        autoList.add(new Auto(7L, 7L, 7L, 3L, 4L, Color.WHITE.name(), 1149000, Transmission.AUTOMATIC.name(), Drive.FWD.name(), BodyStyle.SEDAN.name().replace("_", " ")));
        autoList.add(new Auto(10L, 10L, 10L, 4L, 3L, Color.BROWN.name(), 867000, Transmission.MANUAL.name(), Drive.FWD.name(), BodyStyle.SEDAN.name().replace("_", " ")));
        autoList.add(new Auto(13L, 13L, 13L, 5L, 9L, Color.BLACK.name(), 20000000, Transmission.AUTOMATIC.name(), Drive.AWD.name(), BodyStyle.COUPE.name().replace("_", " ")));
    }

    @Override
    public Auto getAutoById(Long id) {
        for (Auto auto : this.autoList) {
            if (auto.getId().equals(id)) {
                return auto;
            }
        }
        throw new NonExistingAutoException();
    }


    @Override
    public void saveAuto(Auto auto) {
        this.autoList.add(auto);
    }

    @Override
    public List<Auto> findAll() {
        return (!this.autoList.isEmpty()) ? this.autoList : null;
    }

    @Override
    public void delete(Long id) {
        if (!isNull(getAutoById(id))) {
            this.autoList.remove(getAutoById(id));
        } else {
            throw new NonExistingAutoException();
        }
    }
}
