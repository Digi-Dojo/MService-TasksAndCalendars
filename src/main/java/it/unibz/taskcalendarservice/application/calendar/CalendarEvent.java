package it.unibz.taskcalendarservice.application.calendar;

import it.unibz.taskcalendarservice.application.Place;
import it.unibz.taskcalendarservice.application.User;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "calendar_events")
public class CalendarEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private User user;
    private Place place;
    private List<String> tags;// same as on the task (array is better)

    //Constructors
    public CalendarEvent(Long id , String description, LocalDateTime startDate, LocalDateTime endDate, Optional<Place> place, Optional<User> user, Optional<List<String>> tags) {
        this.id = id;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.place = place.orElse(null);
        this.user = user.orElse(null);
        this.tags = tags.orElse(null);
        assert place.isEmpty() || user.isEmpty();
    }

    public CalendarEvent(String description, LocalDateTime startDate, LocalDateTime endDate, Optional<Place> place, Optional<User> user, Optional<List<String>> tags) {
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.place = place.orElse(null);
        this.user = user.orElse(null);
        this.tags = tags.orElse(null);
        assert place.isEmpty() || user.isEmpty();
    }


    //Getters and Setters
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}