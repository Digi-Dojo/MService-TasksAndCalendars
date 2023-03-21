package it.unibz.taskcalendarservice;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private Status status;
    private User user;
    private Place place;
    private List<String> tags; // probabilmente array è meglio per indicizzare

    public Task(String description, Status status) {
        this.description = description;
        this.status = status;
    }
    public Task(String description, Status status, Place place) {
        this.description = description;
        this.status = status;
        this.place = place;
    }
    public Task(String description, Status status, User user) {
        this.description = description;
        this.status = status;
        this.user = user;
    }
    public Task(String description, Status status, List<String> tags) {
        this.description = description;
        this.status = status;
        this.tags = tags;
    }
    public Task(String description, Status status, User user, List<String> tags) {
        this.description = description;
        this.status = status;
        this.user = user;
        this.tags = tags;
    }
    public Task(String description, Status status, Place place, List<String> tags) {
        this.description = description;
        this.status = status;
        this.place = place;
        this.tags = tags;
    }

    public Long getId(){
        return id;
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

    public void addTag(String tag) {
        if (tags == null) {
            tags = new ArrayList<>();
        }
        tags.add(tag);
    }

    public enum Status {
        PENDING, DONE
    }

}
