package it.unibz.taskcalendarservice.calendar.application.kafka;

import it.unibz.taskcalendarservice.calendar.application.event.CalendarEventCreated;
import it.unibz.taskcalendarservice.calendar.application.event.CalendarEventUpdated;
import it.unibz.taskcalendarservice.calendar.domain.CalendarEvent;
import it.unibz.taskcalendarservice.calendar.domain.CalendarEventBroadcaster;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CalendarEventProducer implements CalendarEventBroadcaster {

    @Autowired
    private final KafkaTemplate<String, String> startupKafkaTemplate;

    @Override
    public void emitCalendarEventCreated(CalendarEvent calendarEvent) {
        CalendarEventCreated calendarEventCreatedEvent = new CalendarEventCreated(calendarEvent);
        startupKafkaTemplate.send("event.created", calendarEventCreatedEvent.toJson());
    }

    @Override
    public void emitCalendarEventUpdated(CalendarEvent calendarEvent) {
        CalendarEventUpdated calendarEventUpdateEvent = new CalendarEventUpdated(calendarEvent);
        startupKafkaTemplate.send("event.updated", calendarEventUpdateEvent.toJson());
    }
}