package ru.ncedu.implement;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.ncedu.entity.*;
import ru.ncedu.entity.Auto;
import ru.ncedu.entity.Brand;
import ru.ncedu.entity.Motor;
import ru.ncedu.model.*;
import ru.ncedu.repository.*;
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

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final UserRoleRepository userRoleRepository;

    private final ContactRepository contactRepository;

    private final PasswordEncoder encoder;

    public Map<String, List> createTables() {
        log.info("Creating tables");

        String createBrand = "CREATE TABLE public.brand( " +
                " id bigserial NOT NULL PRIMARY KEY," +
                "name_brand character varying(32) NOT NULL," +
                "name_model character varying(32) NOT NULL," +
                "year character varying(32) NOT NULL" +
                ")";

        String createMotor = "CREATE TABLE public.motor( " +
                " id bigserial NOT NULL PRIMARY KEY," +
                "motor_type character varying(32) NOT NULL," +
                "volume double precision NOT NULL" +
                ")";

        String createImageAuto = "CREATE TABLE public.image_auto(" +
                " id bigserial NOT NULL PRIMARY KEY," +
                "image_name character varying(256) NOT NULL," +
                "raster bytea NOT NULL " +
                ")";

        String createContact = "CREATE TABLE public.contact (" +
                "id bigserial NOT NULL PRIMARY KEY," +
                "email character varying(256) NOT NULL, " +
                "phone character varying(17) NOT NULL" +
                ")";

        String createAuto = "CREATE TABLE public.auto (" +
                " id bigserial NOT NULL PRIMARY KEY," +
                "id_image bigint REFERENCES image_auto (id)," +
                "id_brand bigint NOT NULL REFERENCES brand (id)," +
                "id_contact bigint REFERENCES contact (id)," +
                "id_motor bigint NOT NULL REFERENCES motor (id)," +
                "color character varying(32) NOT NULL," +
                "price double precision NOT NULL," +
                "transmission_type character varying(32) NOT NULL," +
                "drive_type character varying(32) NOT NULL," +
                "body_style character varying(32) NOT NULL " +
                ")";

        String createRole = "CREATE TABLE public.roles (" +
                "id bigserial NOT NULL PRIMARY KEY," +
                "name character varying(20)" +
                ")";

        String createUser = "CREATE TABLE public.users (" +
                "id bigserial NOT NULL PRIMARY KEY," +
                "email character varying(50) UNIQUE, " +
                "password character varying(120), " +
                "username character varying(20) UNIQUE" +
                ")";

        String createUserRole = "CREATE TABLE public.user_roles (" +
                "id bigserial NOT NULL PRIMARY KEY," +
                "user_id bigint NOT NULL REFERENCES users (id), " +
                "role_id bigint NOT NULL REFERENCES roles (id)" +
                ")";

        jdbcTemplate.execute("DROP TABLE auto CASCADE");
        jdbcTemplate.execute("DROP TABLE brand CASCADE");
        jdbcTemplate.execute("DROP TABLE motor CASCADE");
        jdbcTemplate.execute("DROP TABLE contact CASCADE");
        jdbcTemplate.execute("DROP TABLE image_auto CASCADE");
        jdbcTemplate.execute("DROP TABLE users CASCADE");
        jdbcTemplate.execute("DROP TABLE roles CASCADE");
        jdbcTemplate.execute("DROP TABLE user_roles CASCADE");

        jdbcTemplate.execute(createBrand);
        jdbcTemplate.execute(createMotor);
        jdbcTemplate.execute(createImageAuto);
        jdbcTemplate.execute(createContact);
        jdbcTemplate.execute(createAuto);
        jdbcTemplate.execute(createUser);
        jdbcTemplate.execute(createRole);
        jdbcTemplate.execute(createUserRole);

        List<Brand> brandList = getBrandList();
        List<Motor> motorList = getMotorList();
        List<PictureAuto> pictureAutoList = getPictureAuto();
        List<Auto> autoList = getAutoList();
        List<Contact> contactList = getContactList();
        List<User> userList = getUserList();
        List<Role> roleList = getRoleList();
        List<UserRole> userRoleList = getUserRoleList();

        Map<String, List> mapTable = new HashMap();
        mapTable.put("Brand", brandList);
        mapTable.put("Motor", motorList);
        mapTable.put("Auto", autoList);
        mapTable.put("ImageAuto", pictureAutoList);
        mapTable.put("User", userList);
        mapTable.put("Role", roleList);
        mapTable.put("UserRole", userRoleList);
        mapTable.put("Contact", contactList);

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

    public List<User> getUserList() {
        List<User> userList = new ArrayList<>();
        userList.add(new User(1L, "Admin", "admin@yandex.ru", encoder.encode("adminAutoAds")));
        userList.add(new User(2L, "Moderator", "moderator@yandex.ru", encoder.encode("moderatorAutoAds")));
        return userList;
    }

    public List<Role> getRoleList() {
        List<Role> roleList = new ArrayList<>();
        ERole eRole = null;
        roleList.add(new Role(1L, ERole.ROLE_USER));
        roleList.add(new Role(2L, ERole.ROLE_MODERATOR));
        roleList.add(new Role(3L, ERole.ROLE_ADMIN));
        return roleList;
    }

    public List<UserRole> getUserRoleList() {
        List<UserRole> userRoleList = new ArrayList<>();
        userRoleList.add(new UserRole(1L, 1L, 3L));
        userRoleList.add(new UserRole(2L, 2L, 2L));
        return userRoleList;
    }

    public List<Brand> getBrandList() {
        List<Brand> brandList = new ArrayList<>();
        //audi
        brandList.add(new Brand(1L, "AUDI", "A3", "2016"));
        brandList.add(new Brand(2L, "AUDI", "A4", "2019"));
        brandList.add(new Brand(3L, "AUDI", "A8", "2015"));
        //ford
        brandList.add(new Brand(4L, "FORD", "FIESTA", "2016"));
        brandList.add(new Brand(5L, "FORD", "FOCUS", "2019"));
        brandList.add(new Brand(6L, "FORD", "MONDEO", "2013"));
        //honda
        brandList.add(new Brand(7L, "HONDA", "ACCORD", "2015"));
        brandList.add(new Brand(8L, "HONDA", "CROSSTOUR", "2016"));
        brandList.add(new Brand(9L, "HONDA", "JAZZ", "2019"));
        //hyudai
        brandList.add(new Brand(10L, "HYUNDAI", "SOLARIS", "2017"));
        brandList.add(new Brand(11L, "HYUNDAI", "ELANTRA", "2018"));
        brandList.add(new Brand(12L, "HYUNDAI", "SONATA", "2019"));
        //bmw
        brandList.add(new Brand(13L, "BMW", "M8", "2015"));
        brandList.add(new Brand(14L, "BMW", "M5", "2014"));
        brandList.add(new Brand(15L, "BMW", "M6", "2016"));
        //mercedes
        brandList.add(new Brand(16L, "Mercedes-Benz", "GLE AMG", "2016"));
        brandList.add(new Brand(17L, "Mercedes-Benz", "AMG GT", "2018"));
        brandList.add(new Brand(18L, "Mercedes-Benz", "MAYBACH GLS", "2020"));
        //kia
        brandList.add(new Brand(19L, "KIA", "CERATO", "2021"));
        brandList.add(new Brand(20L, "KIA", "K5", "2020"));
        brandList.add(new Brand(21L, "KIA", "Rio X", "2020"));
        return brandList;
    }

    public List<Motor> getMotorList() {
        List<Motor> motorList = new ArrayList<>();
        motorList.add(new Motor(1L, Fuel.DIESEL.name(), 1.0));
        motorList.add(new Motor(2L, Fuel.DIESEL.name(), 1.3));
        motorList.add(new Motor(3L, Fuel.DIESEL.name(), 1.6));
        motorList.add(new Motor(4L, Fuel.DIESEL.name(), 2.0));
        motorList.add(new Motor(5L, Fuel.DIESEL.name(), 2.4));
        motorList.add(new Motor(6L, Fuel.DIESEL.name(), 3.0));
        motorList.add(new Motor(7L, Fuel.DIESEL.name(), 3.5));
        motorList.add(new Motor(8L, Fuel.DIESEL.name(), 4.0));
        motorList.add(new Motor(9L, Fuel.DIESEL.name(), 4.4));
        motorList.add(new Motor(10L, Fuel.DIESEL.name(), 5.0));
        motorList.add(new Motor(11L, Fuel.DIESEL.name(), 5.5));
        return motorList;
    }

    public List<PictureAuto> getPictureAuto() {
        List<PictureAuto> pictureAutoList = new ArrayList<>();
        //audi
        pictureAutoList.add(new PictureAuto(1L, "A3", convertImageAuto("./image-auto/Audi/AUDI-A3.jpg")));
        pictureAutoList.add(new PictureAuto(2L, "A4", convertImageAuto("./image-auto/Audi/AUDI-A4.jpg")));
        pictureAutoList.add(new PictureAuto(3L, "A8", convertImageAuto("./image-auto/Audi/AUDI-A8.jpg")));
        //ford
        pictureAutoList.add(new PictureAuto(4L, "FIESTA", convertImageAuto("./image-auto/Ford/FORD-FIESTA.jpg")));
        pictureAutoList.add(new PictureAuto(5L, "FOCUS", convertImageAuto("./image-auto/Ford/FORD-FOCUS.jpg")));
        pictureAutoList.add(new PictureAuto(6L, "MONDEO", convertImageAuto("./image-auto/Ford/FORD-MONDEO.jpg")));
        //honda
        pictureAutoList.add(new PictureAuto(7L, "ACCORD", convertImageAuto("./image-auto/Honda/HONDA-ACCORD.jpg")));
        pictureAutoList.add(new PictureAuto(8L, "CROSSTOUR", convertImageAuto("./image-auto/Honda/HONDA-CROSSTOUR.jpg")));
        pictureAutoList.add(new PictureAuto(9L, "JAZZ", convertImageAuto("./image-auto/Honda/HONDA-JAZZ.jpg")));
        //hyudai
        pictureAutoList.add(new PictureAuto(10L, "SOLARIS", convertImageAuto("./image-auto/Hyundai/HYUNDAI-SOLARIS.jpg")));
        pictureAutoList.add(new PictureAuto(11L, "ELANTRA", convertImageAuto("./image-auto/Hyundai/HYUNDAI-ELANTRA.jpg")));
        pictureAutoList.add(new PictureAuto(12L, "SONATA", convertImageAuto("./image-auto/Hyundai/HYUNDAI-SONATA.jpg")));
        //bmw
        pictureAutoList.add(new PictureAuto(13L, "M8", convertImageAuto("./image-auto/BMW/BMW-M8.jpg")));
        pictureAutoList.add(new PictureAuto(14L, "M5", convertImageAuto("./image-auto/BMW/BMW-M5.jpg")));
        pictureAutoList.add(new PictureAuto(15L, "M6", convertImageAuto("./image-auto/BMW/BMW-M6.jpg")));
        //mercedes
        pictureAutoList.add(new PictureAuto(16L, "GLE AMG", convertImageAuto("./image-auto/Mercedes/Mersedes-GLE-AMG.jpg")));
        pictureAutoList.add(new PictureAuto(17L, "AMG GT", convertImageAuto("./image-auto/Mercedes/Mersedes-AMG-GT.jpg")));
        pictureAutoList.add(new PictureAuto(18L, "MAYBACH GLS", convertImageAuto("./image-auto/Mercedes/Mersedes-Maybach-GLS.jpg")));
        //kia
        pictureAutoList.add(new PictureAuto(20L, "CERATO", convertImageAuto("./image-auto/Kia/Kia-Cerato.jpg")));
        pictureAutoList.add(new PictureAuto(21L, "K5", convertImageAuto("./image-auto/Kia/Kia-K5.jpg")));
        pictureAutoList.add(new PictureAuto(21L, "RIO X", convertImageAuto("./image-auto/Kia/Kia-Rio-X.jpg")));
        return pictureAutoList;
    }

    public List<Auto> getAutoList() {
        List<Auto> autoList = new ArrayList<>();
        //audi
        autoList.add(new Auto(1L, 1L, 1L, 1L, 4L, Color.GRAY.name(), 1935000, Transmission.DSG.name(), Drive.FWD.name(), BodyStyle.SEDAN.name().replace("_", " ")));
        autoList.add(new Auto(2L, 2L, 2L, 1L, 4L, Color.BLUE.name(), 2850000, Transmission.DSG.name(), Drive.FWD.name(), BodyStyle.STATION_WAGON.name().replace("_", " ")));
        autoList.add(new Auto(3L, 3L, 3L, 1L, 6L, Color.WHITE.name(), 10000000, Transmission.AUTOMATIC.name(), Drive.AWD.name(), BodyStyle.SEDAN.name().replace("_", " ")));
        //ford
        autoList.add(new Auto(4L, 4L, 4L, 2L, 3L, Color.RED.name(), 726000, Transmission.MANUAL.name(), Drive.FWD.name(), BodyStyle.SEDAN.name().replace("_", " ")));
        autoList.add(new Auto(5L, 5L, 5L, 2L, 3L, Color.BLUE.name(), 1440500, Transmission.AUTOMATIC.name(), Drive.FWD.name(), BodyStyle.SEDAN.name().replace("_", " ")));
        autoList.add(new Auto(6L, 6L, 6L, 2L, 4L, Color.WHITE.name(), 2106000, Transmission.AUTOMATIC.name(), Drive.FWD.name(), BodyStyle.SEDAN.name().replace("_", " ")));
        //honda
        autoList.add(new Auto(7L, 7L, 7L, 3L, 4L, Color.WHITE.name(), 1149000, Transmission.AUTOMATIC.name(), Drive.FWD.name(), BodyStyle.SEDAN.name().replace("_", " ")));
        autoList.add(new Auto(8L, 8L, 8L, 3L, 4L, Color.WHITE.name(), 2484000, Transmission.AUTOMATIC.name(), Drive.AWD.name(), BodyStyle.SUV.name().replace("_", " ")));
        autoList.add(new Auto(9L, 9L, 9L, 3L, 2L, Color.PURPLE.name(), 454000, Transmission.MANUAL.name(), Drive.FWD.name(), BodyStyle.HATCHBACK.name().replace("_", " ")));
        //hyudai
        autoList.add(new Auto(10L, 10L, 10L, 4L, 3L, Color.BROWN.name(), 867000, Transmission.MANUAL.name(), Drive.FWD.name(), BodyStyle.SEDAN.name().replace("_", " ")));
        autoList.add(new Auto(11L, 11L, 11L, 4L, 4L, Color.WHITE.name(), 1305000, Transmission.AUTOMATIC.name(), Drive.FWD.name(), BodyStyle.SEDAN.name().replace("_", " ")));
        autoList.add(new Auto(12L, 12L, 12L, 4L, 4L, Color.GRAY.name(), 1425000, Transmission.AUTOMATIC.name(), Drive.FWD.name(), BodyStyle.SEDAN.name().replace("_", " ")));
        //bmw
        autoList.add(new Auto(13L, 13L, 13L, 5L, 9L, Color.BLACK.name(), 20000000, Transmission.AUTOMATIC.name(), Drive.AWD.name(), BodyStyle.COUPE.name().replace("_", " ")));
        autoList.add(new Auto(14L, 14L, 14L, 5L, 9L, Color.GREEN.name(), 15000000, Transmission.MANUAL.name(), Drive.RWD.name(), BodyStyle.SUV.name().replace("_", " ")));
        autoList.add(new Auto(15L, 15L, 15L, 5L, 9L, Color.GRAY.name(), 10000000, Transmission.AUTOMATIC.name(), Drive.FWD.name(), BodyStyle.STATION_WAGON.name().replace("_", " ")));
        //mercedes
        autoList.add(new Auto(16L, 16L, 16L, 6L, 3L, Color.RED.name(), 18000000, Transmission.AUTOMATIC.name(), Drive.AWD.name(), BodyStyle.COUPE.name().replace("_", " ")));
        autoList.add(new Auto(17L, 17L, 17L, 6L, 8L, Color.GRAY.name(), 11999999, Transmission.DSG.name(), Drive.AWD.name(), BodyStyle.LIFTBACK.name().replace("_", " ")));
        autoList.add(new Auto(18L, 18L, 18L, 6L, 8L, Color.RED.name(), 19566308, Transmission.AUTOMATIC.name(), Drive.AWD.name(), BodyStyle.SUV.name().replace("_", " ")));
        //kia
        autoList.add(new Auto(19L, 19L, 19L, 7L, 6L, Color.RED.name(), 1231900, Transmission.MANUAL.name(), Drive.FWD.name(), BodyStyle.SEDAN.name().replace("_", " ")));
        autoList.add(new Auto(20L, 20L, 20L, 7L, 2L, Color.BLUE.name(), 1592900, Transmission.AUTOMATIC.name(), Drive.FWD.name(), BodyStyle.SEDAN.name().replace("_", " ")));
        autoList.add(new Auto(21L, 21L, 21L, 7L, 6L, Color.BLUE.name(), 1005900, Transmission.MANUAL.name(), Drive.FWD.name(), BodyStyle.HATCHBACK.name().replace("_", " ")));
        return autoList;
    }

    public List<Contact> getContactList() {
        List<Contact> contactList = new ArrayList<>();
        contactList.add(new Contact(1L, "audi@gmail.com", "+7(111)-111-11-11"));
        contactList.add(new Contact(2L, "ford@gmail.com", "+7(222)-222-22-22"));
        contactList.add(new Contact(3L, "honda@gmail.com", "+7(333)-333-33-33"));
        contactList.add(new Contact(4L, "hyundai@gmail.com", "+7(444)-444-44-44"));
        contactList.add(new Contact(5L, "bmv@gmail.com", "+7(555)-555-55-55"));
        contactList.add(new Contact(6L, "mercedes@gmail.com", "+7(666)-666-66-66"));
        contactList.add(new Contact(7L, "kia@gmail.com", "+7(777)-777-77-77"));
        return contactList;
    }

    public void uploadBaseData() {
        Map<String, List> listTable = createTables();

        List<Brand> brandList = listTable.get("Brand");
        List<Motor> motorList = listTable.get("Motor");
        List<Auto> autoList = listTable.get("Auto");
        List<PictureAuto> pictureAuto = listTable.get("ImageAuto");
        List<User> userList = listTable.get("User");
        List<Role> roleList = listTable.get("Role");
        List<UserRole> userRoleList = listTable.get("UserRole");
        List<Contact> contactList = listTable.get("Contact");

        brandRepository.saveAll(brandList);
        motorRepository.saveAll(motorList);
        pictureAutoRepository.saveAll(pictureAuto);
        contactRepository.saveAll(contactList);
        autorepository.saveAll(autoList);
        userRepository.saveAll(userList);
        roleRepository.saveAll(roleList);
        userRoleRepository.saveAll(userRoleList);
    }
}
