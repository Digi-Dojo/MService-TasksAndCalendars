package it.unibz.taskcalendarservice.domain.task;

import it.unibz.taskcalendarservice.application.task.Task;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface TaskRepository {
    public Task save(Task task);

    public List<Task> findAll();

    public Optional<Task> findById(long id);

    public void delete(Task task);
}
