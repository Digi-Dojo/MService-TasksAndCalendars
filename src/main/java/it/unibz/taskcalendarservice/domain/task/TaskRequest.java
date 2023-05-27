package it.unibz.taskcalendarservice.domain.task;

import it.unibz.taskcalendarservice.application.Place;
import it.unibz.taskcalendarservice.application.User;
import it.unibz.taskcalendarservice.application.task.Status;

import java.util.List;

public class TaskRequest {
    private String description;
    private Status status;
    private User user;
    private Place place;
    private List<String> tags;

    public TaskRequest() {
    }

    public TaskRequest(String description, Status status, User user, Place place, List<String> tags) {
        this.description = description;
        this.status = status;
        this.user = user;
        this.place = place;
        this.tags = tags;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
