package it.unibz.taskcalendarservice.domain.task;

import it.unibz.taskcalendarservice.application.task.Task;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Optional;

@Configuration
public class MyConfig {
    @Bean
    public TaskRepository taskRepository() {
        TaskRepository taskRepo = new TaskRepository() {
            @Override
            public Task save(Task task) {
                return null;
            }

            @Override
            public List<Task> findAll() {
                return null;
            }

            @Override
            public Optional<Task> findById(long id) {
                return Optional.empty();
            }

            @Override
            public void delete(Task task) {

            }
        };
        return taskRepo;
    }
}
