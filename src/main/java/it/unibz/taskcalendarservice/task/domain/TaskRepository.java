package it.unibz.taskcalendarservice.task.domain;

import it.unibz.taskcalendarservice.task.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}