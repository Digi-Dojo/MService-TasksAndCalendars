package it.unibz.taskcalendarservice.domain.task;

import it.unibz.taskcalendarservice.application.task.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}