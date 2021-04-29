package ru.ncedu.implement;

import org.mockito.Mockito;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.ncedu.interfaces.*;
import javax.sql.DataSource;

@Configuration
public class NewSearchAutoTestConfiguration extends NewSearchAutoServiceImpTest {

    @Bean
    @ConfigurationProperties(prefix = "schema.sql")
    public DataSource dataSource() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource ();
//        dataSource.setDriverClassName ("org.hsqldb.jdbcDriver");
//        dataSource.setUrl ("jdbc: hsqldb: hsql: // localhost:");
//        dataSource.setUsername ("са");
//        dataSource.setPassword ("");
        return  DataSourceBuilder.create().build();
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate();
    }

    @Bean
    public BrandRepository brandRepository() {
        return Mockito.mock(BrandRepository.class);
    }

    @Bean
    public MotorRepository motorRepository() {
        return Mockito.mock(MotorRepository.class);
    }

    @Bean
    public ContactRepository contactRepository() {
        return Mockito.mock(ContactRepository.class);
    }

    @Bean
    public PictureAutoRepository pictureAutoRepository() {
        return Mockito.mock(PictureAutoRepository.class);
    }

    @Bean
    public AutoRepository autoRepository() {
        return Mockito.mock(AutoRepository.class);
    }
}

