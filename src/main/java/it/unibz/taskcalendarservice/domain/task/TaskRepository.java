package it.unibz.taskcalendarservice.domain.task;

import it.unibz.taskcalendarservice.application.task.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    public Task save(Task task);

    public List<Task> findAll();

    public Optional<Task> findById(long id);

    public void delete(Task task);
}
