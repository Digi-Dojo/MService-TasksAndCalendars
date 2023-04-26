package it.unibz.taskcalendarservice.domain.calendar;

import it.unibz.taskcalendarservice.application.User;
import it.unibz.taskcalendarservice.application.calendar.CalendarEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;



@Service
public class ManageCalendarEvents {

    private final CalendarEventRepository calendarEventRepository;

    @Autowired
    public ManageCalendarEvents(CalendarEventRepository calendarEventRepository) {
        this.calendarEventRepository = calendarEventRepository;
    }

    public CalendarEvent createNewCalendarEvent(String name) {
        Optional<CalendarEventService> maybeCalendarEvent = calendarEventRepository.findByName(name);

        if (maybeCalendarEvent.isPresent()) {
            CalendarEventService existing = maybeCalendarEvent.get();
            Long id = existing.getId();
            throw new IllegalArgumentException("Event '" + name + "' already exists: it is id=" + id);
        }

        return calendarEventRepository.save(new CalendarEvent("first event", LocalDateTime.of(2001, 03, 17, 10, 15, 30), LocalDateTime.of(2001, 03, 17, 10, 15, 30), new User((long) Math.pow(-9.223372,18),"Mirko")));
    }
}

