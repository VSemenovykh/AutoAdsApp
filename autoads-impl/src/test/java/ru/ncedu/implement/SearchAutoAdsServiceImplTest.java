package ru.ncedu.implement;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;
import ru.ncedu.model.DataAuto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@DataJpaTest
public class SearchAutoAdsServiceImplTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private SearchAutoAdsServiceImpl searchAutoAdsAdsServiceImpl;

    @Test
    public void injectedComponentsAreNotNull(){
        assertThat(jdbcTemplate).isNotNull();
        assertThat(searchAutoAdsAdsServiceImpl).isNotNull();
    }

    @Test
    @Sql(scripts={"classpath:sql/create_schema.sql",
            "classpath:sql/brand.sql",
            "classpath:sql/motor.sql",
            "classpath:sql/contact.sql",
            "classpath:sql/image_auto.sql",
            "classpath:sql/users.sql",
            "classpath:sql/roles.sql",
            "classpath:sql/user_roles.sql",
            "classpath:sql/auto.sql"})
    public void testResponseHasOkStatusAnd3AutoFoundByAudiSearch(){
        List<String> nameBrand = Collections.singletonList("AUDI");
        ResponseEntity<Map<String, Object>> searchResponse = searchAutoAdsAdsServiceImpl.searchAutoAds(nameBrand,
                null, null, null, null, null,
                null, null, null, null, null,
                null, null, 0, 5);

        assertEquals(HttpStatus.OK, searchResponse.getStatusCode());
        assertEquals(3, ((List<DataAuto>) searchResponse.getBody().get("listAutoAds")).size());
    }

    @Test
    @Sql(scripts={"classpath:sql/create_schema.sql",
            "classpath:sql/brand.sql",
            "classpath:sql/motor.sql",
            "classpath:sql/contact.sql",
            "classpath:sql/image_auto.sql",
            "classpath:sql/users.sql",
            "classpath:sql/roles.sql",
            "classpath:sql/user_roles.sql",
            "classpath:sql/auto.sql"})
    public void testResponseHasNoResponseStatusByBlackAudiSearch(){
        List<String> nameBrand = Collections.singletonList("AUDI");
        List<String> color = Collections.singletonList("BLACK");
        ResponseEntity<Map<String, Object>> searchResponse = searchAutoAdsAdsServiceImpl.searchAutoAds(nameBrand,
                null, null, null, color, null,
                null, null, null, null, null,
                null, null, 0, 5);

        assertEquals(HttpStatus.NO_CONTENT, searchResponse.getStatusCode());
    }

    @Test
    @Sql(scripts={"classpath:sql/create_schema.sql",
            "classpath:sql/brand.sql",
            "classpath:sql/motor.sql",
            "classpath:sql/contact.sql",
            "classpath:sql/image_auto.sql",
            "classpath:sql/users.sql",
            "classpath:sql/roles.sql",
            "classpath:sql/user_roles.sql",
            "classpath:sql/auto.sql"})
    public void testResponseHasOkStatusAnd1AutoFoundByAllPairSearch(){
        List<String> nameBrand = Collections.singletonList("FORD");
        List<String> nameModel = Collections.singletonList("FOCUS");
        String startYear = "2016";
        double startPrice = 1000000.0;
        List<String> color = Collections.singletonList("BLUE");
        List<String> motorType = Collections.singletonList("DIESEL");
        double startVolume = 1.6;
        List<String> driveType = Collections.singletonList("FWD");
        List<String> transmissionType = Collections.singletonList("AUTOMATIC");
        List<String> bodyStyle = Collections.singletonList("SEDAN");
        ResponseEntity<Map<String, Object>> searchResponse = searchAutoAdsAdsServiceImpl.searchAutoAds(nameBrand,
                nameModel, startYear, null, color,startPrice,
                null, motorType,startVolume , null, driveType,
                transmissionType,bodyStyle, 0, 5);

        assertEquals(HttpStatus.OK, searchResponse.getStatusCode());
        assertEquals(1, ((List<DataAuto>) searchResponse.getBody().get("listAutoAds")).size());
    }

    @Test
    @Sql(scripts={"classpath:sql/create_schema.sql",
            "classpath:sql/brand.sql",
            "classpath:sql/motor.sql",
            "classpath:sql/contact.sql",
            "classpath:sql/image_auto.sql",
            "classpath:sql/users.sql",
            "classpath:sql/roles.sql",
            "classpath:sql/user_roles.sql",
            "classpath:sql/auto.sql"})
    public void testResponseHasOkStatusAnd2AutoFoundByColorSearch(){
        List<String> color = new ArrayList<>();
        color.add("RED");
        color.add("WHITE");
        ResponseEntity<Map<String, Object>> searchResponse = searchAutoAdsAdsServiceImpl.searchAutoAds(null,
                null, null, null, color,null,
                null, null,null , null, null,
                null,null, 0, 5);

        assertEquals(HttpStatus.OK, searchResponse.getStatusCode());
        assertEquals(2, ((List<DataAuto>) searchResponse.getBody().get("listAutoAds")).size());
    }

    @Test
    @Sql(scripts={"classpath:sql/create_schema.sql",
            "classpath:sql/brand.sql",
            "classpath:sql/motor.sql",
            "classpath:sql/contact.sql",
            "classpath:sql/image_auto.sql",
            "classpath:sql/users.sql",
            "classpath:sql/roles.sql",
            "classpath:sql/user_roles.sql",
            "classpath:sql/auto.sql"})
    public void testResponseHasOkStatusAnd2AutoFoundByVolumeAndBodyStyleSearch(){
        List<String> bodyStyle =  Collections.singletonList("SEDAN");
        double startVolume = 2.0;
        double endVolume = 2.0;
        ResponseEntity<Map<String, Object>> searchResponse = searchAutoAdsAdsServiceImpl.searchAutoAds(null,
                null, null, null, null,null,
                null, null,startVolume , endVolume, null,
                null,bodyStyle, 0, 5);

        assertEquals(HttpStatus.OK, searchResponse.getStatusCode());
        assertEquals(1, ((List<DataAuto>) searchResponse.getBody().get("listAutoAds")).size());
    }

}
