package ru.ncedu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.ncedu.implement.SearchAutoAdsServiceImpl;
import ru.ncedu.implement.ValidDataAutoAdsImpl;
import ru.ncedu.implement.ValidDataSearchAutoAdsImpl;
import ru.ncedu.repositories.AutoRepository;
import ru.ncedu.repositories.UserRepository;

@SpringBootApplication
public class TestApplication {

    @Autowired
    private AutoRepository autoRepository;
    @Autowired
    private UserRepository userRepository;

    @Bean
    public SearchAutoAdsServiceImpl searchAutoAdsAdsServiceImpl() {
        return new SearchAutoAdsServiceImpl(autoRepository);
    }

    @Bean
    public ValidDataAutoAdsImpl validDataAutoAds() {
        return new ValidDataAutoAdsImpl(userRepository);
    }

    @Bean
    public ValidDataSearchAutoAdsImpl validDataSearchAutoAds() {
        return new ValidDataSearchAutoAdsImpl();
    }
}
