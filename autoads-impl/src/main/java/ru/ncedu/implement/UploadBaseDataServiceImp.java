package ru.ncedu.implement;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.ncedu.entity.*;
import ru.ncedu.model.ERole;
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

        String createContact ="CREATE TABLE public.contact (" +
                "id bigserial NOT NULL PRIMARY KEY,"+
                "email character varying(256) NOT NULL, " +
                "phone character varying(16) NOT NULL"+
                ")";

        String createAuto = "CREATE TABLE public.auto (" +
                " id bigserial NOT NULL PRIMARY KEY," +
                "id_image bigint REFERENCES image_auto (id)," +
                "id_brand bigint NOT NULL REFERENCES brand (id)," +
                "id_contact bigint REFERENCES contact (id)," +
                "id_motor bigint NOT NULL REFERENCES motor (id)," +
                "color character varying NOT NULL," +
                "price double precision," +
                "transmission_type character varying," +
                "drive_type character varying," +
                "body_style character varying " +
                ")";

        String createRole ="CREATE TABLE public.roles (" +
                "id bigserial NOT NULL PRIMARY KEY," +
                "name character varying(20)"+
                ")";

        String createUser ="CREATE TABLE public.users (" +
                "id bigserial NOT NULL PRIMARY KEY," +
                "email character varying(50) UNIQUE, " +
                "password character varying(120), " +
                "username character varying(20) UNIQUE" +
                ")";

        String createUserRole ="CREATE TABLE public.user_roles (" +
                "id bigserial NOT NULL PRIMARY KEY,"+
                "user_id bigint NOT NULL REFERENCES users (id), " +
                "role_id bigint NOT NULL REFERENCES roles (id)"+
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


        List<Brand> brandList = new ArrayList<>();
        brandList.add(new Brand(1L, "BMW", "M8", "2015"));
        brandList.add(new Brand(2L, "BMW", "M5", "2014"));
        brandList.add(new Brand(3L, "BMW", "M6", "2016"));
        brandList.add(new Brand(4L, "Mercedes-Benz", "GLE AMG", "2016"));

        List<Motor> motorList = new ArrayList<>();
        motorList.add(new Motor(1L, "diesel", 4.0));
        motorList.add(new Motor(2L, "diesel", 5.0));
        motorList.add(new Motor(3L, "diesel", 4.4));
        motorList.add(new Motor(4L, "diesel", 3.0));

        List<PictureAuto> pictureAutoList = new ArrayList<>();
        pictureAutoList.add(new PictureAuto(1L, "M8", convertImageAuto("./image-auto/BMW/BMW-M8.jpg")));
        pictureAutoList.add(new PictureAuto(2L, "M5", convertImageAuto("./image-auto/BMW/BMW-M5.jpg")));
        pictureAutoList.add(new PictureAuto(3L, "M6", convertImageAuto("./image-auto/BMW/BMW-M6.jpg")));
        pictureAutoList.add(new PictureAuto(4L, "GLE AMG", convertImageAuto("./image-auto/Mercedes/Mersedes-GLE-AMG.jpg")));


        List<Auto> autoList = new ArrayList<>();
        autoList.add(new Auto(1L, 1L, 1L, 1L,1L, "Black", 20000000, "automatic", "awd", "sedan"));
        autoList.add(new Auto(2L, 2L, 2L, 1L,2L, "White", 15000000, "manual", "rwd", "suv"));
        autoList.add(new Auto(3L, 3L, 3L, 1L,3L, "Gray", 10000000, "automatic", "fwd", "wagon"));
        autoList.add(new Auto(4L, 4L, 4L, 2L,3L, "Red", 18000000, "automatic", "awd", "coupe"));


        List<Contact> contactList = new ArrayList<>();
        contactList.add(new Contact(1L, "bmv@gmail.com", "+7(111)-111-11"));
        contactList.add(new Contact(2L, "mercedes@gmail.com", "+7(222)222-22-22"));

        List<User> userList = new ArrayList<>();
        userList.add(new User(1L, "Admin", "admin@yandex.ru", encoder.encode("adminAutoAds")));
        userList.add(new User(2L, "Moderator", "moderator@yandex.ru", encoder.encode("moderatorAutoAds")));

        List<Role> roleList = new ArrayList<>();
        ERole eRole = null;
        roleList.add(new Role(1L,eRole.ROLE_USER));
        roleList.add(new Role(2L,eRole.ROLE_MODERATOR));
        roleList.add(new Role(3L,eRole.ROLE_ADMIN));

        List<UserRole> userRoleList = new ArrayList<>();
        userRoleList.add(new UserRole(1L,1L, 3L));
        userRoleList.add(new UserRole(2L,2L, 2L));

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

    public void uploadBaseData() throws IOException {
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
