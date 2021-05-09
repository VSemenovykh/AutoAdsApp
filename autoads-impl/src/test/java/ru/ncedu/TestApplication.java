package ru.ncedu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.ncedu.implement.SearchAutoAdsServiceImpl;
import ru.ncedu.repositoryes.AutoRepository;

@SpringBootApplication
public class TestApplication {

    @Autowired
    private AutoRepository autoRepository;

    @Bean
    public SearchAutoAdsServiceImpl searchAutoAdsAdsServiceImpl() {
        return new SearchAutoAdsServiceImpl(autoRepository);
    }

}
