package it.unibz.taskcalendarservice.domain.calendar;

import it.unibz.taskcalendarservice.application.calendar.CalendarEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface CalendarEventRepository extends JpaRepository<CalendarEvent,Long> {
    public List<CalendarEvent> findAll();

    // public Optional<Object> findById(Long id);

    public CalendarEvent save(CalendarEvent calendarEvent);

    public void deleteById(Long id);

    Optional<CalendarEventService> findByName(String name);
}
