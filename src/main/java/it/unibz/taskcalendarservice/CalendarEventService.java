package it.unibz.taskcalendarservice;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalendarEventService {
    private final CalendarEventRepository calendarEventRepository;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public CalendarEventService(CalendarEventRepository calendarEventRepository) {
        this.calendarEventRepository = calendarEventRepository;
    }

    public CalendarEvent createCalendarEvent(CalendarEvent calendarEvent) {
        return calendarEventRepository.save(calendarEvent);
    }

    public CalendarEvent updateCalendarEvent(CalendarEvent calendarEvent) {
        return calendarEventRepository.save(calendarEvent);
    }

    public List<CalendarEvent> getAllCalendarEvents() {
        return calendarEventRepository.findAll();
    }

    public CalendarEvent getCalendarEventById(Long id) {
        return (CalendarEvent) calendarEventRepository.findById(id).orElse(null);
    }

    public void deleteCalendarEventById(Long id) {
        calendarEventRepository.deleteById(id);
    }
}
