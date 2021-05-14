package ru.ncedu.implement;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.ncedu.model.DataSearchAuto;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@DataJpaTest
class ValidDataSearchAutoAdsImplTest {

    @Autowired
    private ValidDataSearchAutoAdsImpl validDataSearchAutoAds;

    @Test
    public void injectedComponentsAreNotNull(){
        assertThat(validDataSearchAutoAds).isNotNull();
    }

    @Test
    public void testValidDataSearchAutoAdsOk(){
        DataSearchAuto dataSearchAuto = new DataSearchAuto();
        dataSearchAuto.setNameBrand(Collections.singletonList("AUDI"));
        dataSearchAuto.setBodyStyleType(Collections.singletonList("SEDAN"));

        assertTrue(validDataSearchAutoAds.checkDataSearchAutoAds(dataSearchAuto));
    }

    @Test
    public void testNotValidDataSearchAutoAdsFailed(){
        DataSearchAuto dataSearchAuto = new DataSearchAuto();
        dataSearchAuto.setNameBrand(Collections.singletonList("AUDI"));
        dataSearchAuto.setBodyStyleType(Collections.singletonList("SEDAN"));
        dataSearchAuto.setColor(Collections.singletonList("AUDI"));

        assertFalse(validDataSearchAutoAds.checkDataSearchAutoAds(dataSearchAuto));
    }
}
