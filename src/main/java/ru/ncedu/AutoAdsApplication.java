package ru.ncedu;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.ncedu.service.LoadBDServiceImp;

@Slf4j
@SpringBootApplication
@RequiredArgsConstructor
public class AutoAdsApplication implements CommandLineRunner {

    final private LoadBDServiceImp loadBDServiceImp;
    public static void main(String[] args) {

        SpringApplication.run(AutoAdsApplication.class, args);
    }

    @Override
    public void run(String... args) {

        log.info("StartApplication...");
        log.info("Load data to postgresql");

        loadBDServiceImp.upload();

        log.info("Successfully, data loaded!");

    }
}
