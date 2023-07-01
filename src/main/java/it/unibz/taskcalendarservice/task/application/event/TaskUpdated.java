package it.unibz.taskcalendarservice.task.application.event;

import it.unibz.taskcalendarservice.task.domain.Task;
import lombok.Getter;

public class TaskUpdated {

    private static final String TASK_UPDATED = "Task updated";

    @Getter
    private String type = TASK_UPDATED ;

    @Getter
    private Task payload;

    public TaskUpdated(Task payload){this.payload = payload;}

    public String toJson(){
        return "{" +
                "\"taskID\": \"" + payload.getId() + "\"," +
                "\"description\": \"" + payload.getDescription() + "\"," +
                "\"place\": \"" + payload.getPlace() + "\"," +
                "\"place\": \"" + payload.getStatus() + "\"," +
                "\"tags\": \"" + payload.getTags() + "\"" +
                "\"title\": \"" + payload.getTitle() + "\"," +
                "\"user\": \"" + payload.getUser() + "\"" +
                "}" +
                "}";
    }
}
