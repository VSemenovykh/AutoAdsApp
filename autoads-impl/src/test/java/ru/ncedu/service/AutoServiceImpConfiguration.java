package ru.ncedu.service;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import ru.ncedu.implement.AutoServiceImp;

@Profile("test")
@Configuration
public class AutoServiceImpConfiguration {

    @Bean
    @Primary
    public AutoService autoService() {
        return Mockito.mock(AutoServiceImp.class);
    }
}
