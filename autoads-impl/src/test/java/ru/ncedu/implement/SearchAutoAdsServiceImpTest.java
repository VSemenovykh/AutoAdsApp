package ru.ncedu.implement;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;
import ru.ncedu.implement.SearchAutoAdsServiceImpl;
import ru.ncedu.model.DataAuto;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@DataJpaTest
public class SearchAutoAdsServiceImpTest {

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
    @Sql(scripts={"classpath:create_schema.sql",
            "classpath:brand.sql",
            "classpath:motor.sql",
            "classpath:contact.sql",
            "classpath:image_auto.sql",
            "classpath:auto.sql"})
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
    @Sql(scripts={"classpath:create_schema.sql",
            "classpath:brand.sql",
            "classpath:motor.sql",
            "classpath:contact.sql",
            "classpath:image_auto.sql",
            "classpath:auto.sql"})
    public void testResponseHasNoResponseStatusByBlackAudiSearch(){
        List<String> nameBrand = Collections.singletonList("AUDI");
        List<String> color = Collections.singletonList("BLACK");
        ResponseEntity<Map<String, Object>> searchResponse = searchAutoAdsAdsServiceImpl.searchAutoAds(nameBrand,
                null, null, null, color, null,
                null, null, null, null, null,
                null, null, 0, 5);
        assertEquals(HttpStatus.NO_CONTENT, searchResponse.getStatusCode());
    }
}
