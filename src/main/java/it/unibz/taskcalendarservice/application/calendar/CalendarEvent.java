package it.unibz.taskcalendarservice.application.calendar;

import it.unibz.taskcalendarservice.application.Place;
import it.unibz.taskcalendarservice.application.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CalendarEvent {

    private String description;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private User user;
    private Place place;
    private List<String> tags;// same as on the task (array is better)

    //Constructors
    public CalendarEvent(String description, LocalDateTime startDate, LocalDateTime endDate) {
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }


    public CalendarEvent(String description, LocalDateTime startDate, LocalDateTime endDate, Place place) {
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.place = place;
    }

    public CalendarEvent(String description, LocalDateTime startDate, LocalDateTime endDate, User user) {
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.user = user;
    }

    public CalendarEvent(String description, LocalDateTime startDate, LocalDateTime endDate, List<String> tags) {
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.tags = tags;
    }
    
    public CalendarEvent(String description, LocalDateTime startDate, LocalDateTime endDate, Place place, List<String> tags) {
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.place = place;
        this.tags = tags;
    }

    public CalendarEvent(String description, LocalDateTime startDate, LocalDateTime endDate, User user, List<String> tags) {
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.user = user;
        this.tags = tags;
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

}
