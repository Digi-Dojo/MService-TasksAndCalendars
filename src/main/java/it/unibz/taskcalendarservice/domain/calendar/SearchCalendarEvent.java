package it.unibz.taskcalendarservice.domain.calendar;

import it.unibz.taskcalendarservice.application.calendar.CalendarEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SearchCalendarEvent {
    CalendarEventRepository calendarEventRepository;

    @Autowired
    public SearchCalendarEvent(CalendarEventRepository calendarEventRepository){
        this.calendarEventRepository = calendarEventRepository;
    }

    public CalendarEvent findById(Long id){
        Optional<CalendarEvent> calendarEvent = calendarEventRepository.findById(id);
        if (calendarEvent.isEmpty()) throw new IllegalArgumentException("Calendar event with id '" + id + "' does not exist");
        return calendarEvent.get();
    }

    public List<CalendarEvent> getAll(){
        List<CalendarEvent> calendarEvents = calendarEventRepository.findAll();
        if (calendarEvents.size() == 0) throw new IllegalArgumentException("No calendar events were found");
        return calendarEvents;
    }
}
