package it.unibz.taskcalendarservice.calendar.domain;

import it.unibz.taskcalendarservice.common.domain.Place;
import it.unibz.taskcalendarservice.common.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CRUDCalendarEvent {
    private final CalendarEventRepository calendarEventRepository;
    private final SearchCalendarEvent searchCalendarEvent;

    @Autowired
    public CRUDCalendarEvent(CalendarEventRepository calendarEventRepository, SearchCalendarEvent searchCalendarEvent) {
        this.calendarEventRepository = calendarEventRepository;
        this.searchCalendarEvent = searchCalendarEvent;
    }

    public CalendarEvent createCalendarEvent(String description, LocalDateTime startDate, LocalDateTime endDate, Optional<Place> place,
                                             Optional<User> user, Optional<List<String>> tags){
        return calendarEventRepository.save(new CalendarEvent(description, startDate, endDate, place, user, tags));
    }

    public CalendarEvent updateCalendarEvent(Long calendarEventID, Optional<String> description, Optional<LocalDateTime> startDate,
                                             Optional<LocalDateTime> endDate, Optional<Place> place, Optional<User> user, Optional<List<String>> tags){
        CalendarEvent toBeModified = searchCalendarEvent.findById(calendarEventID);
        description.ifPresent(toBeModified::setDescription);
        startDate.ifPresent(toBeModified::setStartDate);
        endDate.ifPresent(toBeModified::setEndDate);
        user.ifPresent(toBeModified::setUser);
        place.ifPresent(toBeModified::setPlace);
        tags.ifPresent(toBeModified::setTags);
        assert user.isEmpty() || place.isEmpty();
        return calendarEventRepository.save(toBeModified);
    }

    public boolean deleteCalendarEvent(Long id){
        Optional<CalendarEvent> toBeRemoved = calendarEventRepository.findById(id);
        if(toBeRemoved.isEmpty()) throw new IllegalArgumentException("Calendar event with id '" + id + "' does not exist");
        calendarEventRepository.delete(toBeRemoved.get());
        try{
            calendarEventRepository.findById(id);
        }catch (Exception e){
            System.out.println("Calendar event successfully deleted");
            return true;
        }
        System.out.println("Process unsuccessful, Calendar event has not been deleted");
        return false;
    }

}