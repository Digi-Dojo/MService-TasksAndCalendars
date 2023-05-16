package it.unibz.taskcalendarservice.domain.calendar;

import it.unibz.taskcalendarservice.application.calendar.CalendarEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalendarEventRepository extends JpaRepository<CalendarEvent, Long>{
}