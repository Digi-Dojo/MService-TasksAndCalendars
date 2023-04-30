package it.unibz.taskcalendarservice.domain.task;

import it.unibz.taskcalendarservice.application.Place;
import it.unibz.taskcalendarservice.application.User;
import it.unibz.taskcalendarservice.application.task.Task;
import it.unibz.taskcalendarservice.application.task.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
<<<<<<< HEAD:src/main/java/it/unibz/taskcalendarservice/TaskService.java

=======
>>>>>>> af024f8d9bdb44c7d9ebd9abc53e806c7faabef5:src/main/java/it/unibz/taskcalendarservice/domain/task/TaskService.java
@Service
public class TaskService {
//    This class should contain business logic related to Tasks, including methods for creating,
//    updating, and deleting Task entities. It should also provide methods for retrieving Task entities from the TaskRepository

    private TaskRepository taskRepo;

    //This class manages the tasks in the local TaskRepository
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    public TaskService(TaskRepository taskRepo){
        this.taskRepo = taskRepo;
    }

    //Create methods for each constructor of the Task class
    public Task createTask(String desc, Status status){
        return taskRepo.save(new Task(desc, status));
    }
    public Task createTask(String desc, Status status, Place place){
        return taskRepo.save(new Task(desc, status, place));
    }
    public Task createTask(String desc, Status status, User user){
        return taskRepo.save(new Task(desc, status, user));
    }
    public Task createTask(String desc, Status status, List<String> tags){
        return taskRepo.save(new Task(desc, status, tags));
    }
    public Task createTask(String desc, Status status, User user, List<String> tags){
        return taskRepo.save(new Task(desc, status, user, tags));
    }
    public Task createTask(String desc, Status status, Place place, List<String> tags){
        return taskRepo.save(new Task(desc, status, place, tags));
    }

    public List<Task> findAll() {
        return taskRepo.findAll();
    }

    public Task getTaskById(long id) {
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

    public boolean updateTaskStatus(Long id, Status newStatus){
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

        if(wantedTask.get().getUser() != null)
            wantedTask.get().setUser(newUser);
        else{
            if(wantedTask.get().getTags() != null)
                wantedTask = Optional.of(new Task(wantedTask.get().getDescription(), wantedTask.get().getStatus(), newUser, wantedTask.get().getTags()));
            else
                wantedTask = Optional.of(new Task(wantedTask.get().getDescription(), wantedTask.get().getStatus(), newUser));

        }
        return Objects.equals(wantedTask.get().getUser(), newUser);
    }

    public boolean updateTaskPlace(Long id, Place newPlace){
        Optional<Task> wantedTask = taskRepo.findById(id);

        if (wantedTask.isEmpty()) {
            throw new IllegalArgumentException("Task with Id '" + id + "' not found");
        }

        if(wantedTask.get().getPlace() != null)
            wantedTask.get().setPlace(newPlace);
        else{
            if(wantedTask.get().getTags() != null)
                wantedTask = Optional.of(new Task(wantedTask.get().getDescription(), wantedTask.get().getStatus(), newPlace, wantedTask.get().getTags()));
            else
                wantedTask = Optional.of(new Task(wantedTask.get().getDescription(), wantedTask.get().getStatus(), newPlace));
        }
        return Objects.equals(wantedTask.get().getPlace(), newPlace);
    }

    public boolean updateTaskTags(Long id, List<String> newTags){
        Optional<Task> wantedTask = taskRepo.findById(id);

        if (wantedTask.isEmpty()) {
            throw new IllegalArgumentException("Task with Id '" + id + "' not found");
        }

        if(wantedTask.get().getTags() != null)
            wantedTask.get().setTags(newTags);
        else{
            if(wantedTask.get().getPlace() != null)
                wantedTask = Optional.of(new Task(wantedTask.get().getDescription(), wantedTask.get().getStatus(), wantedTask.get().getPlace(), newTags));
            else if(wantedTask.get().getUser() != null)
                wantedTask = Optional.of(new Task(wantedTask.get().getDescription(), wantedTask.get().getStatus(), wantedTask.get().getUser(), newTags));
            else
                wantedTask = Optional.of(new Task(wantedTask.get().getDescription(), wantedTask.get().getStatus(), newTags));

        }
        return Objects.equals(wantedTask.get().getTags(), newTags);
    }

    public boolean deleteTask(Long id){
        Optional<Task> wantedTask = taskRepo.findById(id);

        if (wantedTask.isEmpty()) {
            throw new IllegalArgumentException("Task with Id '" + id + "' not found");
        }

        taskRepo.delete(wantedTask.get());
        
        return taskRepo.findById(id).isEmpty();
    }

    public Task save(Task task) {
        return taskRepo.save(task);
    }
}