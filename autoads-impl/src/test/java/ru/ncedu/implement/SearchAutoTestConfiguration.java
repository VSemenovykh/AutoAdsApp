package ru.ncedu.implement;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.ncedu.interfaces.*;

import javax.sql.DataSource;

//@Profile("SearchAutoImp-test")
@Configuration
public class SearchAutoTestConfiguration {

    @Bean
    @Primary
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate();
    }

    @Bean
    @Primary
    public BrandRepository brandRepository() {
        return Mockito.mock(BrandRepository.class);
    }

    @Bean
    @Primary
    public MotorRepository motorRepository() {
        return Mockito.mock(MotorRepository.class);
    }

    @Bean
    @Primary
    public ContactRepository contactRepository() {
        return Mockito.mock(ContactRepository.class);
    }

    @Bean
    @Primary
    public PictureAutoRepository pictureAutoRepository() {
        return Mockito.mock(PictureAutoRepository.class);
    }

    @Bean
    @Primary
    public AutoRepository autoRepository() {
        return Mockito.mock(AutoRepository.class);
    }
}
