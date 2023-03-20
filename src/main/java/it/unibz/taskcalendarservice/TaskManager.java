package it.unibz.taskcalendarservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TaskManager {
    private TaskRepository taskRepo;

    @Autowired
    public TaskManager(TaskRepository taskRepo){
        this.taskRepo = taskRepo;
    }

    public Task createTask(String desc, Task.Status status, User user, Place place, List<String> tags){
        return taskRepo.save(new Task(desc, status, user, place, tags));
    }

    public Task findTask(Long id){
        Optional<Task> wantedTask = taskRepo.findById(id);

        if (wantedTask.isEmpty()) {
            throw new IllegalArgumentException("Task with Id '" + id + "' not found");
        }

        return wantedTask.get();
    }

    public boolean updateTaskDescription(Long id, String newDescription){
        Optional<Task> wantedTask = taskRepo.findById(id);

        if (wantedTask.isEmpty()) {
            throw new IllegalArgumentException("Task with Id '" + id + "' not found");
        }

        wantedTask.get().setDescription(newDescription);
        return Objects.equals(wantedTask.get().getDescription(), newDescription);
    }

    public boolean updateTaskStatus(Long id, Task.Status newStatus){
        Optional<Task> wantedTask = taskRepo.findById(id);

        if (wantedTask.isEmpty()) {
            throw new IllegalArgumentException("Task with Id '" + id + "' not found");
        }

        wantedTask.get().setStatus(newStatus);
        return Objects.equals(wantedTask.get().getStatus(), newStatus);
    }

    public boolean updateTaskUser(Long id, User newUser){
        Optional<Task> wantedTask = taskRepo.findById(id);

        if (wantedTask.isEmpty()) {
            throw new IllegalArgumentException("Task with Id '" + id + "' not found");
        }

        wantedTask.get().setUser(newUser);
        return Objects.equals(wantedTask.get().getUser(), newUser);
    }

    public boolean updateTaskPlace(Long id, Place newPlace){
        Optional<Task> wantedTask = taskRepo.findById(id);

        if (wantedTask.isEmpty()) {
            throw new IllegalArgumentException("Task with Id '" + id + "' not found");
        }

        wantedTask.get().setPlace(newPlace);
        return Objects.equals(wantedTask.get().getPlace(), newPlace);
    }

    public boolean updateTaskTags(Long id, List<String> newTags){
        Optional<Task> wantedTask = taskRepo.findById(id);

        if (wantedTask.isEmpty()) {
            throw new IllegalArgumentException("Task with Id '" + id + "' not found");
        }

        wantedTask.get().setTags(newTags);
        return Objects.equals(wantedTask.get().getPlace(), newTags);
    }
}
