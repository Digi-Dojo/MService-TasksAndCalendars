package it.unibz.taskcalendarservice.task.domain;

import it.unibz.taskcalendarservice.common.domain.Place;
import it.unibz.taskcalendarservice.common.domain.User;
import jakarta.persistence.Transient;

import java.util.List;
import java.util.Optional;

public class CreateTaskDTO {
    private String description, title;
    private Status status;
    private User user;
    private Place place;
    private List<String> tags; // probabilmente array è meglio per indicizzare

    public CreateTaskDTO(){}

    public CreateTaskDTO(String description, Optional<Place> place, Status status, Optional<List<String>> tags, String title,  Optional<User> user) {
        this.description = description;
        this.title = title;
        this.status = status;
        this.place = place.orElse(null);
        this.user = user.orElse(null);
        this.tags = tags.orElse(null);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
