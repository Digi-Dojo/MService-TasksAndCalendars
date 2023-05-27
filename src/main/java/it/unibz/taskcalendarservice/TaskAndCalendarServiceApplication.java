package it.unibz.taskcalendarservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
@EnableAutoConfiguration
@EnableJpaRepositories({"it.unibz.taskcalendarservice.domain.calendar", "it.unibz.taskcalendarservice.domain.task"})
public class TaskAndCalendarServiceApplication {
    public static void main(String[] args) throws UnknownHostException {
        SpringApplication.run(TaskAndCalendarServiceApplication.class, args);
        System.out.println(InetAddress.getLocalHost().getHostAddress());
    }
}