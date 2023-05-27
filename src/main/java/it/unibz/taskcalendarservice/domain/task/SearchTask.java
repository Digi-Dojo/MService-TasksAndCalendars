package it.unibz.taskcalendarservice.domain.task;

import it.unibz.taskcalendarservice.application.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SearchTask {
    TaskRepository taskRepository;

    @Autowired
    public SearchTask(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }

    public Task findById(Long id){
        Optional<Task> task = taskRepository.findById(id);
        if (task.isEmpty()) throw new IllegalArgumentException("Task with id '" + id + "' does not exist");
        return task.get();
    }

    public List<Task> getAll(){
        List<Task> tasks = taskRepository.findAll();
        if (tasks.size() == 0) throw new IllegalArgumentException("No tasks were found");
        return tasks;
    }
}