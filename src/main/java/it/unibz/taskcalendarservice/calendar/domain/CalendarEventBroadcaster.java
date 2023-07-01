package it.unibz.taskcalendarservice.calendar.domain;

public interface CalendarEventBroadcaster {
    void emitCalendarEventCreated(CalendarEvent calendar);
    void emitCalendarEventUpdated(CalendarEvent calendar);
}
