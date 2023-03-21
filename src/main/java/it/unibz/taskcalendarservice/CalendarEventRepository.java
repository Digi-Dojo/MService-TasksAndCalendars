package it.unibz.taskcalendarservice;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CalendarEventRepository {
    List<CalendarEvent> getAllCalendarEvents();

    CalendarEvent getCalendarEventById(long id);

    CalendarEvent save(CalendarEvent calendarEvent);

    List<CalendarEvent> findAll();

    Optional<Object> findById(Long id);

    void deleteById(Long id);
//    This interface should provide methods for persisting and retrieving CalendarEvent entities to/from a data store
}
