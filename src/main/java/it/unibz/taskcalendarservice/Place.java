package it.unibz.taskcalendarservice;

public class Place {
    private final Long id;
    private final String name;

    public Place(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}