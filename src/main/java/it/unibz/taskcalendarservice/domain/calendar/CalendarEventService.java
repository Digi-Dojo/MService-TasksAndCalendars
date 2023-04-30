package it.unibz.taskcalendarservice.domain.calendar;

import it.unibz.taskcalendarservice.application.calendar.CalendarEvent;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CalendarEventService {

    private CalendarEventRepository calendarEventRepository;

    public List<CalendarEvent> findAll() {
        return calendarEventRepository.findAll();
    }

    public Optional<Object> findById(Long id) {
        return calendarEventRepository.findById(id);
    }

    public CalendarEvent save(CalendarEvent calendarEvent) {
        return calendarEventRepository.save(calendarEvent);
    }

    public void deleteById(Long id) {
        calendarEventRepository.deleteById(id);
    }

    public CalendarEvent update(CalendarEvent calendarEvent) {
        return calendarEventRepository.save(calendarEvent);
    }
}
