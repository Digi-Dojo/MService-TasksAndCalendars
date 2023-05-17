package it.unibz.taskcalendarservice.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories({"it.unibz.taskcalendarservice.domain.calendar", "it.unibz.taskcalendarservice.domain.task"})
@EntityScan({"it.unibz.taskcalendarservice.application.calendar", "it.unibz.taskcalendarservice.application.task"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}