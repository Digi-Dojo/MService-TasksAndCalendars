package it.unibz.taskcalendarservice.calendar.domain;

import it.unibz.taskcalendarservice.calendar.domain.CalendarEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalendarEventRepository extends JpaRepository<CalendarEvent, Long>{
}