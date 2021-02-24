package ru.ncedu;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Slf4j
@SpringBootApplication
public class AutoAdsApplication implements WebMvcConfigurer, CommandLineRunner{

    public static void main(String[] args) {

        SpringApplication.run(AutoAdsApplication.class, args);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){

        registry.addResourceHandler("static/**").addResourceLocations("classpath:/static/");
    }

    @Override
    public void run(String... args){

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
