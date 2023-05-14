package it.unibz.taskcalendarservice.Task.domain;

import it.unibz.taskcalendarservice.Task.application.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@SuppressWarnings("unchecked")
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    public Task save(Task task);

    public List<Task> findAll();

    public Optional<Task> findById(long id);

    public void delete(Task task);

    Task save(Task task, Class<Task> taskClass);
}
