package ru.ncedu.implement;

import java.util.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.CollectionUtils;
import ru.ncedu.entity.Brand;
import ru.ncedu.interfaces.AutoRepository;
import ru.ncedu.model.DataAuto;
import javax.sql.DataSource;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
//@ExtendWith(SpringExtension.class)
//@DataJpaTest
//@ContextConfiguration(classes = NewSearchAutoTestConfiguration.class)
public class NewSearchAutoAdsServiceImpTest extends NewSearchAutoTestConfiguration {

//    @Autowired
    private JdbcTemplate jdbcTemplate;

//    @Autowired
    private DataSource dataSource;

//    @Autowired
    private AutoRepository autorepository;

//    private EmbeddedDatabase embeddedDatabase;

    private AutoRepository autoRepository;

    @BeforeEach
    public void setUp() {
//        embeddedDatabase = new EmbeddedDatabaseBuilder()
//                .addDefaultScripts()
//                .setType(EmbeddedDatabaseType.H2)
//                .build();

        jdbcTemplate = new JdbcTemplate();
        autoRepository = autoRepository();
    }

    @Test
    void injectedComponentsAreNotNull(){
        assertThat(jdbcTemplate).isNotNull();
    }

    @Test
//    @Sql("data.sql")
    public void testSave() {
//        jdbcTemplate = new JdbcTemplate(dataSource);
        Brand brand = jdbcTemplate.queryForObject("SELECT * FROM brand WHERE id = ?1", Brand.class);
        System.out.println("");
    }

    @Test
//    @Sql("data.sql")
    public void testSearchAutoPage() {
        List<String> nameBrand = new ArrayList<>(Arrays.asList("BMW"));
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

            pageTuts = autorepository.searchAutoAds(nameBrand,
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
            response.put("listAutoJoin", listDataAuto);
            response.put("currentPage", pageTuts.getNumber());
            response.put("totalAutoJoin", pageTuts.getTotalElements());
            response.put("totalPages", pageTuts.getTotalPages());

            log.info("Status: ok");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
