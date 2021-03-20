package ru.ncedu.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import ru.ncedu.entity.Auto;
import ru.ncedu.entity.Brand;
import ru.ncedu.entity.Motor;
import ru.ncedu.repository.AutoRepository;
import ru.ncedu.repository.BrandRepository;
import ru.ncedu.repository.MotorRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoadBDServiceImp implements LoadBaseDataService{

    private final JdbcTemplate jdbcTemplate;

    private final AutoRepository autorepository;

    private final BrandRepository brandRepository;

    private final MotorRepository motorRepository;

    public Map<String, List> createTables(){
        log.info("Creating tables");

        String createBrand = "CREATE TABLE public.brand( " +
                "id bigserial NOT NULL PRIMARY KEY" +
                ",name_brand character varying(32) NOT NULL" +
                ",name_model character varying(32) NOT NULL" +
                ",year character varying(32) NOT NULL" +
                ")";

        String createMotor = "CREATE TABLE public.motor( " +
                "id bigserial NOT NULL PRIMARY KEY" +
                ",motor_type character varying(32) NOT NULL" +
                ",volume double precision NOT NULL" +
                ")";

        String createAuto = "CREATE TABLE public.auto (" +
                " id bigserial NOT NULL PRIMARY KEY" +
                ",id_brand bigint NOT NULL REFERENCES brand (id)" +
                ",id_motor bigint NOT NULL REFERENCES motor (id)" +
                ",color character varying NOT NULL" +
                ",price double precision" +
                ",transmission_type character varying" +
                ",drive_type character varying" +
                ",body_style character varying " +
                ")";

        jdbcTemplate.execute("DROP TABLE auto CASCADE");
        jdbcTemplate.execute("DROP TABLE brand CASCADE");
        jdbcTemplate.execute("DROP TABLE motor CASCADE");

        jdbcTemplate.execute(createBrand);

        jdbcTemplate.execute(createMotor);

        jdbcTemplate.execute(createAuto);

        List<Brand> brandList = new ArrayList<>();
        brandList.add(new Brand(1L, "BMW", "M8", "2015", null));
        brandList.add(new Brand(2L, "BMW", "M5", "2014",null));
        brandList.add(new Brand(3L, "BMW", "M6", "2016",null));
        brandList.add(new Brand(4L, "Mercedes-Benz", "GLE AMG", "2016",null));

        List<Motor> motorList = new ArrayList<>();
        motorList.add(new Motor(1L, "diseal", 4.0,null));
        motorList.add(new Motor(2L, "electric", 5.0,null));
        motorList.add(new Motor(3L, "diseal", 4.4,null));

        List<Auto>  autoList = new ArrayList<>();
        autoList.add(new Auto(1L, 1L, "Black", 20000000, 1L, "automatic", "awd","sedan",null ,null));
        autoList.add(new Auto(2L, 2L, "White", 15000000, 2L, "manual", "rwd","suv", null ,null));
        autoList.add(new Auto(3L, 3L, "Gray", 10000000, 3L, "automatic", "fwd","wagon", null ,null));

        Map<String, List> mapTable = new HashMap();
        mapTable.put("Brand", brandList);
        mapTable.put("Motor", motorList);
        mapTable.put("Auto", autoList);

        return mapTable;
    }

    public void upload(){
        Map<String, List> listTable =  createTables();
        List<Brand> brandList = listTable.get("Brand");
        List<Motor> motorList = listTable.get("Motor");
        List<Auto> autoList = listTable.get("Auto");

        for(Brand brand: brandList){
            brandRepository.save(brand);
        }

        for(Motor motor: motorList){
            motorRepository.save(motor);
        }

        for(ru.ncedu.entity.Auto auto: autoList){
            autorepository.save(auto);
        }
    }
}
