package it.unibz.taskcalendarservice.domain.calendar;

import it.unibz.taskcalendarservice.application.calendar.CalendarEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalendarEventRepository extends JpaRepository<CalendarEvent, Long>{
}
