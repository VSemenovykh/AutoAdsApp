package ru.ncedu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.ncedu.entity.User;
import ru.ncedu.repository.UserRepository;

@SpringBootApplication
public class AutoAdsApplication   implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(AutoAdsApplication.class);

    @Autowired
    private UserRepository repository;
    public static void main(String[] args) {
        SpringApplication.run(AutoAdsApplication.class, args);

    }
    @Override
    public void run(String... args) {
        log.info("StartApplication...");

        repository.save(new User("Pety","client"));
        repository.save(new User("Mary","admin"));
        repository.save(new User("Sasha","client"));

        System.out.println("\nfindAll()");
        repository.findAll().forEach(x -> System.out.println(x));

        System.out.println("\nfindById(1L)");
        repository.findById(1l).ifPresent(x -> System.out.println(x));

        System.out.println("\nfindByName('Node')");
        repository.findByName("Mary").forEach(x -> System.out.println(x));
    }
}
