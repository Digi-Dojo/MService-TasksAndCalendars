package it.unibz.taskcalendarservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>{

    Task createTask(Task task);

    Task updateTask(Task task);

    Task getTaskById(long id);

    List<Task> getAllTasks();

    List<Task> getTasksByUser(User user);

    List<Task> getTasksByPlace(Place place);

    List<Task> getTasksByTag(String tag);

}
