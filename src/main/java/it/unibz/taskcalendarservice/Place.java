package it.unibz.taskcalendarservice;

import javax.persistence.*;
import java.util.List;

@Entity
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;

    @OneToMany(mappedBy = "place")
    private List<Task> tasks;

    @OneToMany(mappedBy = "place")
    private List<CalendarEvent> calendarEvents;

    // Getters and setters
}
