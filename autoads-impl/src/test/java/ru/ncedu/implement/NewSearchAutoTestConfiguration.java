package ru.ncedu.implement;

import org.mockito.Mockito;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.ncedu.interfaces.*;
import javax.sql.DataSource;

@Configuration
public class NewSearchAutoTestConfiguration{

//    @Bean
//    @ConfigurationProperties(prefix = "schema.sql")
//    public DataSource dataSource() {
////        DriverManagerDataSource dataSource = new DriverManagerDataSource ();
////        dataSource.setDriverClassName ("org.hsqldb.jdbcDriver");
////        dataSource.setUrl ("jdbc: hsqldb: hsql: // localhost:");
////        dataSource.setUsername ("са");
////        dataSource.setPassword ("");
//        return  DataSourceBuilder.create().build();
//    }

    @Bean(name = "h2DataSource")
    @Primary
    public DataSource h2DataSource(){
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.h2.Driver");
        dataSourceBuilder.url("jdbc:h2:file:C:/Users/user/IdeaProjects/Backend-AutoAdsApp/autoads-impl/src/test/resources");
        dataSourceBuilder.username("sa");
        dataSourceBuilder.password("");
        return dataSourceBuilder.build();
    }

    @Bean
    @Primary
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(h2DataSource());
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

