package it.unibz.taskcalendarservice.domain;

import it.unibz.taskcalendarservice.domain.task.TaskRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("it.unibz.taskcalendarservice.domain")
public class AppConfig {
    @Bean
    public TaskRepository taskRepository() {
        return null;
    }
}

