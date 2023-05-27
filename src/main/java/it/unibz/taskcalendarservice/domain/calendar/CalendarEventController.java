package it.unibz.taskcalendarservice.domain.calendar;

import it.unibz.taskcalendarservice.application.calendar.CalendarEvent;
import it.unibz.taskcalendarservice.domain.trello.TrelloIntegrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/calendar-event")
public class CalendarEventController {
    private final CRUDCalendarEvent crudCalendarEvent;
    private final SearchCalendarEvent searchCalendarEvent;
    private final TrelloIntegrationService trelloIntegrationService;

    @Autowired
    public CalendarEventController(CRUDCalendarEvent crudCalendarEvent,
                                   SearchCalendarEvent searchCalendarEvent,
                                   TrelloIntegrationService trelloIntegrationService) {
        this.crudCalendarEvent = crudCalendarEvent;
        this.searchCalendarEvent = searchCalendarEvent;
        this.trelloIntegrationService = trelloIntegrationService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CalendarEvent> findById(@PathVariable("id") Long id) {
        CalendarEvent event = searchCalendarEvent.findById(id);
        return ResponseEntity.ok(event);
    }

    @PostMapping("/create")
    public ResponseEntity<CalendarEvent> createCalendarEvent(
            @RequestBody CalendarEvent request) {
        CalendarEvent event = crudCalendarEvent.createCalendarEvent(
                request.getDescription(),
                request.getStartDate(),
                request.getEndDate(),
                Optional.ofNullable(request.getPlace()),
                Optional.ofNullable(request.getUser()),
                Optional.ofNullable(request.getTags())
        );

        // Create Trello card from the calendar event
        trelloIntegrationService.createTrelloCardFromEvent(event);

        return ResponseEntity.ok(event);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CalendarEvent> updateCalendarEvent(
            @PathVariable("id") Long id, @RequestBody CalendarEvent request) {
        CalendarEvent event = crudCalendarEvent.updateCalendarEvent(
                id,
                Optional.ofNullable(request.getDescription()),
                Optional.ofNullable(request.getStartDate()),
                Optional.ofNullable(request.getEndDate()),
                Optional.ofNullable(request.getPlace()),
                Optional.ofNullable(request.getUser()),
                Optional.ofNullable(request.getTags())
        );

        // Update Trello card with the modified event details
        trelloIntegrationService.updateTrelloCard(event);

        return ResponseEntity.ok(event);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCalendarEvent(@PathVariable("id") Long id) {
        boolean deleted = crudCalendarEvent.deleteCalendarEvent(id);

        if (deleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<CalendarEvent>> getAll() {
        List<CalendarEvent> events = searchCalendarEvent.getAll();
        return ResponseEntity.ok(events);
    }
}