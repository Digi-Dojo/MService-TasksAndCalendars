package it.unibz.taskcalendarservice.Task.domain;

import it.unibz.taskcalendarservice.Task.application.Task;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    public <T> T save(Task task, Class<T> type);

    public @NotNull List<Task> findAll();

    public Optional<Task> findById(long id);

    public void delete(@NotNull Task task);
}
