package ru.ncedu.implement;

import java.util.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.CollectionUtils;
import ru.ncedu.entity.Brand;
import ru.ncedu.repositoryes.AutoRepository;
import ru.ncedu.repositoryes.BrandRepository;
import ru.ncedu.model.DataAuto;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
//@RunWith(SpringRunner.class)
//@TestPropertySource(properties = {
//        "spring.jpa.hibernate.ddl-auto=create-drop",
//        "spring.main.banner-mode=off",
//        "spring.datasource.platform=h2"
//})
@Sql("../../../../resources/create_schema.sql")
@ExtendWith(SpringExtension.class)
@DataJpaTest
public class SearchAutoAdsServiceImpAdsTest extends SearchAutoAdsTestConfiguration {

    private JdbcTemplate jdbcTemplate;

    //????
    @Autowired
    private AutoRepository autoRepository;

    //????
    @Autowired
    private BrandRepository brandRepository;

    @Before
    public void setUp() {
        jdbcTemplate = jdbcTemplate();
//        brandRepository = Mockito.mock(BrandRepository.class);
//        autoRepository = Mockito.mock(AutoRepository.class);
    }

    @Test
    public void injectedComponentsAreNotNull(){
        assertThat(jdbcTemplate).isNotNull();
    }

    @Test
    @Sql("../../../../resources/brand.sql")
    public void testSave() {
        jdbcTemplate.update("INSERT INTO brand(id, name_brand, name_model, year) VALUES(1, 'AUDI', 'A3', '2016')");
        Brand brand = jdbcTemplate.queryForObject("SELECT * FROM brand WHERE id = ?1", Brand.class);
        assertThat(brand).isNotNull();
    }

    @Test
    @Sql("../../../../resources/brand.sql")
    @Sql("../../../../resources/motor.sql")
    @Sql("../../../../resources/contact.sql")
    @Sql("../../../../resources/image_auto.sql")
    @Sql("../../../../resources/auto.sql")
    public void testSearchAutoPage() {
        List<String> nameBrand = new ArrayList<>(Collections.singletonList("BMW"));
        List<String> nameModel = new ArrayList<>();
        String startYear = null;
        String endYear = null;
        List<String> color = new ArrayList<>(Arrays.asList("BLACK", "RED"));
        Double startPrice = null;
        Double endPrice = null;
        List<String> motorType = new ArrayList<>();
        Double startVolume = null;
        Double endVolume = null;
        List<String> drive = new ArrayList<>();
        List<String> transmission = new ArrayList<>();
        List<String> bodyStyle = new ArrayList<>();
        int page = 0;
        int size = 3;

        try {
            List<DataAuto> listDataAuto;
            Pageable paging = PageRequest.of(page, size);

            Page<DataAuto> pageTuts;
            nameBrand = CollectionUtils.isEmpty(nameBrand) ? null : nameBrand;
            nameModel = CollectionUtils.isEmpty(nameModel) ? null : nameModel;
            color = CollectionUtils.isEmpty(color) ? null : color;
            motorType = CollectionUtils.isEmpty(motorType) ? null : motorType;
            drive = CollectionUtils.isEmpty(drive) ? null : drive;
            transmission = CollectionUtils.isEmpty(transmission) ? null : transmission;
            bodyStyle = CollectionUtils.isEmpty(bodyStyle) ? null : bodyStyle;

            pageTuts = autoRepository.searchAutoAds(nameBrand,
                                                    nameModel,
                                                    startYear,
                                                    endYear,
                                                    color,
                                                    startPrice,
                                                    endPrice,
                                                    motorType,
                                                    startVolume,
                                                    endVolume,
                                                    drive,
                                                    transmission,
                                                    bodyStyle,
                                                    paging);

            listDataAuto = pageTuts.getContent();
            if (listDataAuto.isEmpty()) {
                log.info("List empty");
            }

            Map<String, Object> response = new HashMap<>();
            response.put("listAutoAds", listDataAuto);
            response.put("currentPage", pageTuts.getNumber());
            response.put("totalAutoAds", pageTuts.getTotalElements());
            response.put("totalPages", pageTuts.getTotalPages());

            log.info("Status: ok");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
