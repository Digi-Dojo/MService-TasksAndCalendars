package it.unibz.taskcalendarservice.calendar.domain;

import it.unibz.taskcalendarservice.common.domain.Place;
import it.unibz.taskcalendarservice.common.domain.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class CreateCalendarEventDTO {
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    private String title;
    private User user;
    private Place place;
    private List<String> tags;

    public CreateCalendarEventDTO(){}

    public CreateCalendarEventDTO(String title, String description, LocalDateTime startDate, LocalDateTime endDate, Optional<User> user, Optional<Place> place, Optional<List<String>> tags){
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
