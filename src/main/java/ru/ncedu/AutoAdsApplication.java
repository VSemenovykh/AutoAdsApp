package ru.ncedu;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.ncedu.entity.User;
import ru.ncedu.repository.UserRepository;

@Slf4j
@SpringBootApplication
public class AutoAdsApplication   implements CommandLineRunner {
    @Autowired
    private UserRepository repository;
    public static void main(String[] args) {
        SpringApplication.run(AutoAdsApplication.class, args);

    }
    @Override
    public void run(String... args) {
        log.info("StartApplication...");

        /*to check the connection with postgres*/
        repository.save(new User(1L,"Pety","client"));
        repository.save(new User(2L,"Mary","admin"));
        repository.save(new User(3L,"Sasha","client"));

        System.out.println("\nfindAll()");
        repository.findAll().forEach(x -> System.out.println(x));

        System.out.println("\nfindById(1L)");
        repository.findById(1l).ifPresent(x -> System.out.println(x));

        System.out.println("\nfindByName('Node')");
        repository.findByName("Mary").forEach(x -> System.out.println(x));
    }
}
