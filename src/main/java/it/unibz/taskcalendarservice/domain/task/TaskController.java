package it.unibz.taskcalendarservice.domain.task;

import it.unibz.taskcalendarservice.application.Place;
import it.unibz.taskcalendarservice.application.User;
import it.unibz.taskcalendarservice.application.task.Status;
import it.unibz.taskcalendarservice.application.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/tasks")
public class TaskController {

    private final CRUDTask crudTask;
    private final SearchTask searchTask;

    @Autowired
    public TaskController(CRUDTask crudTask, SearchTask searchTask) {
        this.crudTask = crudTask;
        this.searchTask = searchTask;
    }

    @GetMapping("/{id}")
    public Task findById(@PathVariable("id")Long id){
        return searchTask.findById(id);
    }

     @PostMapping("/create")
    public Task createTask(String description, Status status, Optional<User> user, Optional<Place> place, Optional<List<String>> tags){
        return crudTask.createTask(description, status, user, place, tags);
    }

    @PostMapping("/update/{id}")
    public Task updateTask(Long taskID, Optional<String> description, Optional<Status> status, Optional<User> user, Optional<Place> place, Optional<List<String>> tags){
        return crudTask.updateTask(taskID, description, status, user, place, tags);
    }

    @DeleteMapping("/delete/{id}")
    public boolean deleteTask(Long taskID){
        return crudTask.deleteTask(taskID);
    }

    @GetMapping("/getAll")
    public List<Task> getAll(){
        return searchTask.getAll();
    }
}