package it.unibz.taskcalendarservice.task.application.event;

import lombok.Getter;

public class TaskCreated {

    private static final String TASK_CREATED = "Task created";

    @Getter
    private String type = TASK_CREATED ;

    @Getter
    private it.unibz.taskcalendarservice.task.domain.Task payload;

    public TaskCreated(it.unibz.taskcalendarservice.task.domain.Task payload){
    this.payload = payload;
    }

    public String toJson(){
        return "{" +
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
