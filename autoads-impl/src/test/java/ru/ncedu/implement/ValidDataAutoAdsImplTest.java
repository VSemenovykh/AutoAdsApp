package ru.ncedu.implement;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import ru.ncedu.model.DataAuto;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@DataJpaTest
class ValidDataAutoAdsImplTest {

    @Autowired
    private ValidDataAutoAdsImpl validDataAutoAdsImpl;

    @Test
    public void injectedComponentsAreNotNull(){
        assertThat(validDataAutoAdsImpl).isNotNull();
    }

    @Test
    public void testValidDataAutoAdsOk(){
        Date date = new GregorianCalendar().getTime();
        String formattedDate = new SimpleDateFormat("d MMM yyyy HH:mm:ss", Locale.US).format(date);

        DataAuto dataAuto = new DataAuto();
        dataAuto.setNameBrand("AUDI");
        dataAuto.setNameModel("A3");
        dataAuto.setYear("2016");
        dataAuto.setColor("GRAY");
        dataAuto.setMotorType("DIESEL");
        dataAuto.setVolume(2.0);
        dataAuto.setDriveType("FWD");
        dataAuto.setTransmissionType("DSG");
        dataAuto.setBodyStyleType("SEDAN");
        dataAuto.setEmail("audi@gmail.com");
        dataAuto.setPhone("+7(111)-111-11-11");
        dataAuto.setUsername("Admin");
        dataAuto.setAddingDate("5/May/2021/14:00:00");

        assertTrue(validDataAutoAdsImpl.checkDataAutoAds(dataAuto));
    }

    @Test
    public void testNotValidDataAutoAds(){
        DataAuto dataAuto = new DataAuto();
        dataAuto.setNameBrand("AUDI");
        dataAuto.setNameModel("A3");
        dataAuto.setYear("2016");
        dataAuto.setColor("GRAY");
        dataAuto.setMotorType("SEDAN");
        dataAuto.setVolume(-2.0);
        dataAuto.setDriveType("FWD");
        dataAuto.setTransmissionType("DSG");
        dataAuto.setBodyStyleType("SEDAN");
        dataAuto.setEmail("audi@gmail.com");
        dataAuto.setPhone("+7(111)+111-11-11");
        dataAuto.setUsername("Admin");
        dataAuto.setAddingDate("5 May 2021 14:00:00");

        assertFalse(validDataAutoAdsImpl.checkDataAutoAds(dataAuto));
    }

}
