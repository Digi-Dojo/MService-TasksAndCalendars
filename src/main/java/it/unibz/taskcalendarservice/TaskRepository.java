package it.unibz.taskcalendarservice;

import java.util.List;

public interface TaskRepository {

    Task createTask(Task task);

    Task updateTask(Task task);

    Task getTaskById(long id);

    List<Task> getAllTasks();

    List<Task> getTasksByUser(User user);

    List<Task> getTasksByPlace(Place place);

    List<Task> getTasksByTag(String tag);

}
