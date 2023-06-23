package it.unibz.taskcalendarservice.task.application;

import it.unibz.taskcalendarservice.common.domain.place.Place;
import it.unibz.taskcalendarservice.common.domain.user.User;
import it.unibz.taskcalendarservice.task.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(path = "api/tasks")
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
    public Task findById(@PathVariable("id")Long id){
        return searchTask.findById(id);
    }

    @PostMapping("/create")
    public Task createTask(@RequestBody CreateTaskDTO createTaskDTO){
        return crudTask.createTask( createTaskDTO.getDescription(), Optional.ofNullable(createTaskDTO.getPlace()), createTaskDTO.getStatus(),
                Optional.ofNullable(createTaskDTO.getTags()), createTaskDTO.getTitle(), Optional.ofNullable(createTaskDTO.getUser()));
    }

    @PostMapping("/update/{id}")
    public Task updateTask(
            @PathVariable("id") Long id,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) Status status,
            @RequestParam(required = false) User user,
            @RequestParam(required = false) Place place,
            @RequestParam(required = false) List<String> tags
    ) {
        return  crudTask.updateTask(
                id,
                Optional.ofNullable(description),
                Optional.ofNullable(status),
                Optional.ofNullable(user),
                Optional.ofNullable(place),
                Optional.ofNullable(tags)
        );
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
