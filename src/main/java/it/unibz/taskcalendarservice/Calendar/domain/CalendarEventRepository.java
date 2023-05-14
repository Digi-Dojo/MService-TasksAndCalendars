package it.unibz.taskcalendarservice.Calendar.domain;

import it.unibz.taskcalendarservice.Calendar.application.CalendarEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalendarEventRepository extends JpaRepository<CalendarEvent, Long>{
}
