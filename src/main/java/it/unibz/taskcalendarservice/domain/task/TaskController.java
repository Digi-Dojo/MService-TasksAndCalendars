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

    /*
    Test: curl http://localhost:8080/api/tasks/sayhello
    run in terminal
     */
    @GetMapping(value = "/sayhello")
    public String sayHelloWorld(){
        return "\nHello World!";
    }

    @PostMapping("/{id}")
    public Task findById(@PathVariable("id") Long id){
        return searchTask.findById(id);
    }

    @PostMapping("/create")
    public Task createTask(@RequestBody TaskRequest taskRequest) {
        String description = taskRequest.getDescription();
        Status status = taskRequest.getStatus();
        User user = taskRequest.getUser();
        Place place = taskRequest.getPlace();
        List<String> tags = taskRequest.getTags();

        return crudTask.createTask(description, status, Optional.ofNullable(user), Optional.ofNullable(place), Optional.ofNullable(tags));
    }


    @PostMapping("/update/{id}")
    public Task updateTask(@PathVariable("id") Long taskID, @RequestBody TaskRequest taskRequest) {
        String description = taskRequest.getDescription();
        Status status = taskRequest.getStatus();
        User user = taskRequest.getUser();
        Place place = taskRequest.getPlace();
        List<String> tags = taskRequest.getTags();

        return crudTask.updateTask(taskID, Optional.ofNullable(description), Optional.ofNullable(status), Optional.ofNullable(user), Optional.ofNullable(place), Optional.ofNullable(tags));
    }

    @DeleteMapping("/delete/{id}")
    public boolean deleteTask(@PathVariable("id") Long taskID){
        return crudTask.deleteTask(taskID);
    }

    @GetMapping("/getAll")
    public List<Task> getAll(){
        return searchTask.getAll();
    }
}