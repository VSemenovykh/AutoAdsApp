package ru.ncedu.implement;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import ru.ncedu.entity.Auto;
import ru.ncedu.entity.Brand;
import ru.ncedu.entity.PictureAuto;
import ru.ncedu.entity.Motor;
import ru.ncedu.repository.AutoRepository;
import ru.ncedu.repository.BrandRepository;
import ru.ncedu.repository.PictureAutoRepository;
import ru.ncedu.repository.MotorRepository;
import ru.ncedu.service.UploadBaseDataService;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class UploadBaseDataServiceImp implements UploadBaseDataService {

    private final JdbcTemplate jdbcTemplate;

    private final AutoRepository autorepository;

    private final BrandRepository brandRepository;

    private final MotorRepository motorRepository;

    private final PictureAutoRepository pictureAutoRepository;

    public Map<String, List> createTables() {
        log.info("Creating tables");

        String createBrand = "CREATE TABLE public.brand( " +
                " id bigserial NOT NULL PRIMARY KEY" +
                ",name_brand character varying(32) NOT NULL" +
                ",name_model character varying(32) NOT NULL" +
                ",year character varying(32) NOT NULL" +
                ")";

        String createMotor = "CREATE TABLE public.motor( " +
                " id bigserial NOT NULL PRIMARY KEY" +
                ",motor_type character varying(32) NOT NULL" +
                ",volume double precision NOT NULL" +
                ")";

        String createImageAuto = "CREATE TABLE public.image_auto(" +
                " id bigserial NOT NULL PRIMARY KEY" +
                ",image_name character varying(256) NOT NULL" +
                ",raster bytea NOT NULL " +
                ")";

        String createAuto = "CREATE TABLE public.auto (" +
                " id bigserial NOT NULL PRIMARY KEY" +
                ",id_image bigint REFERENCES image_auto (id)" +
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
        jdbcTemplate.execute("DROP TABLE image_auto CASCADE");

        jdbcTemplate.execute(createBrand);

        jdbcTemplate.execute(createMotor);

        jdbcTemplate.execute(createImageAuto);

        jdbcTemplate.execute(createAuto);

        List<Brand> brandList = new ArrayList<>();
        brandList.add(new Brand(1L, "BMW", "M8", "2015"));
        brandList.add(new Brand(2L, "BMW", "M5", "2014"));
        brandList.add(new Brand(3L, "BMW", "M6", "2016"));
        brandList.add(new Brand(4L, "Mercedes-Benz", "GLE AMG", "2016"));

        List<Motor> motorList = new ArrayList<>();
        motorList.add(new Motor(1L, "diesel", 4.0));
        motorList.add(new Motor(2L, "diesel", 5.0));
        motorList.add(new Motor(3L, "diesel", 4.4));

        List<PictureAuto> pictureAutoList = new ArrayList<>();
        pictureAutoList.add(new PictureAuto(1L, "M8", convertImageAuto("C:/IdeaProjects/ProjectByNetcracker/image-auto/BMW/BMW-M8.jpg")));
        pictureAutoList.add(new PictureAuto(2L, "M5", convertImageAuto("C:/IdeaProjects/ProjectByNetcracker/image-auto/BMW/BMW-M5.jpg")));
        pictureAutoList.add(new PictureAuto(3L, "M6", convertImageAuto("C:/IdeaProjects/ProjectByNetcracker/image-auto/BMW/BMW-M6.jpg")));

        List<Auto> autoList = new ArrayList<>();
        autoList.add(new Auto(1L, 1L, 1L, 1L, "Black", 20000000, "automatic", "awd", "sedan"));
        autoList.add(new Auto(2L, 2L, 2L, 2L, "White", 15000000, "manual", "rwd", "suv"));
        autoList.add(new Auto(3L, 3L, 3L, 3L, "Gray", 10000000, "automatic", "fwd", "wagon"));

        Map<String, List> mapTable = new HashMap();
        mapTable.put("Brand", brandList);
        mapTable.put("Motor", motorList);
        mapTable.put("Auto", autoList);
        mapTable.put("ImageAuto", pictureAutoList);

        return mapTable;
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

    public void uploadBaseData() throws IOException {
        Map<String, List> listTable = createTables();
        List<Brand> brandList = listTable.get("Brand");
        List<Motor> motorList = listTable.get("Motor");
        List<Auto> autoList = listTable.get("Auto");
        List<PictureAuto> pictureAuto = listTable.get("ImageAuto");

        brandRepository.saveAll(brandList);
        motorRepository.saveAll(motorList);
        pictureAutoRepository.saveAll(pictureAuto);
        autorepository.saveAll(autoList);
    }
}
