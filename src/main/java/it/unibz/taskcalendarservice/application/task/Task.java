package it.unibz.taskcalendarservice.application.task;

import it.unibz.taskcalendarservice.application.Place;
import it.unibz.taskcalendarservice.application.User;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private Status status;
    private User user;
    private Place place;
    private List<String> tags; // probabilmente array è meglio per indicizzare

    //Constructors

    public Task(String description, Status status, Optional<User> user, Optional<Place> place, Optional<List<String>> tags) {
        this.description = description;
        this.status = status;
        this.user = user.orElse(null);
        this.place = place.orElse(null);
        this.tags = tags.orElse(null);

        assert user.isEmpty() || place.isEmpty();
    }


    //Getters and Setters
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

    //Methods
    public void addTag(String tag) {
        if (tags == null) {
            tags = new ArrayList<>();
        }
        tags.add(tag);
    }
}