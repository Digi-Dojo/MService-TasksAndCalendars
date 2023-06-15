package it.unibz.taskcalendarservice.task.application.event;

import it.unibz.taskcalendarservice.task.domain.Task;
import lombok.Getter;

public class TaskDeleted {
    private static final String TASK_DELETED = "Task deleted";

    @Getter
    private String type = TASK_DELETED ;

    @Getter
    private Task payload;

    public TaskDeleted(Task payload){this.payload = payload;}

    public String toJson(){
        return "{" +
                "\"taskID\": \"" + payload.getId() + "\"," +
                "}";
    }
}
