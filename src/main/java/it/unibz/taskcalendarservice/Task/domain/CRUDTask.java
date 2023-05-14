package it.unibz.taskcalendarservice.Task.domain;

import it.unibz.taskcalendarservice.Place;
import it.unibz.taskcalendarservice.User;
import it.unibz.taskcalendarservice.Task.application.Status;
import it.unibz.taskcalendarservice.Task.application.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CRUDTask {
    private final TaskRepository taskRepository;
    private final SearchTask searchTask;

    @Autowired
    public CRUDTask(TaskRepository taskRepository, SearchTask searchTask) {
        this.taskRepository = taskRepository;
        this.searchTask = searchTask;
    }

    public Task createTask(String description, Status status, Optional<User> user, Optional<Place> place, Optional<List<String>> tags){
        return taskRepository.save(new Task(description, status, user, place, tags), Task.class);
    }

    public Task updateTask(Long taskID, Optional<String> description, Optional<Status> status, Optional<User> user, Optional<Place> place, Optional<List<String>> tags){
        Task toBeModified = searchTask.findById(taskID);
        return taskRepository.save(toBeModified, Task.class);
    }

    public boolean deleteTask(Long id){
        Optional<Task> toBeRemoved = taskRepository.findById(id);
        if(toBeRemoved.isEmpty()) throw new IllegalArgumentException("Task with id '" + id + "' does not exist");
        taskRepository.delete(toBeRemoved.get());
        try{
            taskRepository.findById(id);
        }catch (Exception e){
            System.out.println("Task successfully deleted");
            return true;
        }
        System.out.println("Process unsuccessful, Task has not been deleted");
        return false;
    }

}
