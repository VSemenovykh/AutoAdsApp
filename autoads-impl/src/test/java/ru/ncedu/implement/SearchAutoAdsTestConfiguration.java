package ru.ncedu.implement;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import javax.sql.DataSource;

@Configuration
public class SearchAutoAdsTestConfiguration {

    @Bean
    public DataSource DataSource(){
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.h2.Driver");
        dataSourceBuilder.url("jdbc:h2:mem:testdb");
        dataSourceBuilder.username("sa");
        dataSourceBuilder.password("");
        return dataSourceBuilder.build();
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(DataSource());
    }

//    @Bean
//    public BrandRepository brandRepository() {
//        return Mockito.mock(BrandRepository.class);
//    }
//
//    @Bean
//    public MotorRepository motorRepository() {
//        return Mockito.mock(MotorRepository.class);
//    }
//
//    @Bean
//    public ContactRepository contactRepository() {
//        return Mockito.mock(ContactRepository.class);
//    }
//
//    @Bean
//    public PictureAutoRepository pictureAutoRepository() {
//        return Mockito.mock(PictureAutoRepository.class);
//    }
//
//    @Bean
//    public AutoRepository autoRepository() {
//        return Mockito.mock(AutoRepository.class);
//    }
}

