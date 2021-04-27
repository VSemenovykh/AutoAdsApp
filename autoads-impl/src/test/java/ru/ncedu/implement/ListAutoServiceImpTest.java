package ru.ncedu.implement;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.ncedu.model.*;
import ru.ncedu.services.ListAutoService;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ListAutoServiceImpTest {

    @Mock
    ListAutoService listAutoService;

    @Test
    void testFindAllAutoJoinPage() {
        Map<String, Object> mapDataAuto = new HashMap<>();
        List<DataAuto> listDataAuto = new ArrayList<>();
        listDataAuto.add(new DataAuto(1L, 1L, convertImageAuto("src/test/resources/image-auto/Audi/AUDI-A3.JPG"), "audi@gmail.com", "+7(111)-111-11-11", "AUDI", "A3", "2016", Color.GRAY.name(), 1935000.0, Fuel.DIESEL.name(), 2.0, Transmission.DSG.name(), Drive.FWD.name(), BodyStyle.SEDAN.name().replace("_", " ")));
        listDataAuto.add(new DataAuto(2L, 2L, convertImageAuto("src/test/resources/image-auto/Audi/AUDI-A4.JPG"), "audi@gmail.com", "+7(111)-111-11-11", "AUDI", "A4", "2019", Color.BLUE.name(), 2850000.0, Fuel.DIESEL.name(), 2.0, Transmission.DSG.name(), Drive.FWD.name(), BodyStyle.STATION_WAGON.name().replace("_", " ")));
        listDataAuto.add(new DataAuto(3L, 3L, convertImageAuto("src/test/resources/image-auto/Audi/AUDI-A8.JPG"), "audi@gmail.com", "+7(111)-111-11-11", "AUDI", "A8", "2015", Color.WHITE.name(), 10000000.0, Fuel.DIESEL.name(), 3.0, Drive.AWD.name(), Transmission.AUTOMATIC.name(), BodyStyle.SEDAN.name().replace("_", " ")));
        listDataAuto.add(new DataAuto(4L, 4L, convertImageAuto("src/test/resources/image-auto/Ford/FORD-FIESTA.JPG"), "ford@gmail.com", "+7(222)-222-22-22", "FORD", "FIESTA", "2016", Color.RED.name(), 726000.0, Fuel.DIESEL.name(), 1.6, Transmission.MANUAL.name(), Drive.FWD.name(), BodyStyle.SEDAN.name().replace("_", " ")));
        listDataAuto.add(new DataAuto(5L, 5L, convertImageAuto("src/test/resources/image-auto/Ford/FORD-FOCUS.JPG"), "ford@gmail.com", "+7(222)-222-22-22", "FORD", "FOCUS", "2019", Color.BLUE.name(), 1440500.0, Fuel.DIESEL.name(), 1.6, Drive.FWD.name(), Transmission.AUTOMATIC.name(), BodyStyle.SEDAN.name().replace("_", " ")));

        listDataAuto.add(new DataAuto());
        mapDataAuto.put("listAutoJoin", listDataAuto);
        mapDataAuto.put("currentPage", 0);
        mapDataAuto.put("totalAutoJoin", 5);
        mapDataAuto.put("totalPages", 4);

        when(listAutoService.findAllAutoJoinPage(0, 5)).thenReturn(new ResponseEntity<>(mapDataAuto, HttpStatus.OK));

        ResponseEntity<Map<String, Object>> resMapDataAuto = listAutoService.findAllAutoJoinPage(0, 5);
        assert resMapDataAuto.getBody().get("listAutoJoin") != null;
        assert resMapDataAuto.getBody().get("listAutoJoin").equals(listDataAuto);
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
