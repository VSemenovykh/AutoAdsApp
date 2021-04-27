package ru.ncedu.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import ru.ncedu.model.DataSearchAuto;
import ru.ncedu.services.SearchAutoService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class SearchAutoControllerTest {

    @InjectMocks
    SearchAutoController searchAutoController;

    @Mock
    SearchAutoService searchAutoService;

    //?????????????
    @Test
    public void testSearchAutoPage(){
        //given
        DataSearchAuto dataSearchAuto = new DataSearchAuto();
        dataSearchAuto.setNameBrand(new ArrayList<>(Arrays.asList("AUDI")));
        dataSearchAuto.setNameModel(null);
        dataSearchAuto.setStartPrice(null);
        dataSearchAuto.setEndPrice(null);
        dataSearchAuto.setColor(null);
        dataSearchAuto.setStartYear(null);
        dataSearchAuto.setEndYear(null);
        dataSearchAuto.setStartVolume(null);
        dataSearchAuto.setEndVolume(null);
        dataSearchAuto.setMotorType(null);
        dataSearchAuto.setDriveType(null);
        dataSearchAuto.setTransmissionType(null);
        dataSearchAuto.setBodyStyleType(null);
        String page = "0";
        String size = "3";

        // when
        ResponseEntity<Map<String, Object>> result = searchAutoController.searchAutoPage(dataSearchAuto, page, size);
    }

}
