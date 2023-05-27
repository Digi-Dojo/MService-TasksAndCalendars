package it.unibz.taskcalendarservice.application.task;

import it.unibz.taskcalendarservice.application.Place;
import it.unibz.taskcalendarservice.application.User;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Optional<User> user;

    @ManyToOne
    @JoinColumn(name = "place_id")
    private Optional<Place> place;

    @ElementCollection
    @CollectionTable(name = "task_tags", joinColumns = @JoinColumn(name = "task_id"))
    @Column(name = "tag")
    private Optional<List<String>> tags;


    public Task(String description, Status status, Optional<User> user, Optional<Place> place, Optional<List<String>> tags) {
        this.description = description;
        this.status = status;
        this.user = user;
        this.place = place;
        this.tags = tags;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Optional<User> getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = Optional.ofNullable(user);
    }

    public Optional<Place> getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = Optional.ofNullable(place);
    }

    public void setTags(List<String> tags) {
        this.tags = Optional.ofNullable(tags);
    }
}

