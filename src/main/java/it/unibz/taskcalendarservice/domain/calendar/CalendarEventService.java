package it.unibz.taskcalendarservice.domain.calendar;

import it.unibz.taskcalendarservice.application.calendar.CalendarEvent;
import org.springframework.stereotype.Service;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;
import java.util.Optional;

@Service
public class CalendarEventService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;
    private CalendarEventRepository calendarEventRepository;

    public List<CalendarEvent> findAll() {
        return calendarEventRepository.findAll();
    }

    public Optional<Object> findById(Long id) {
        return calendarEventRepository.findById(id);
    }

    public void deleteById(Long id) {
        calendarEventRepository.deleteById(id);
    }

    public CalendarEvent update(CalendarEvent calendarEvent) {
        return calendarEventRepository.save(calendarEvent);
    }

    public Long getId() {
        return id;
    }
}
