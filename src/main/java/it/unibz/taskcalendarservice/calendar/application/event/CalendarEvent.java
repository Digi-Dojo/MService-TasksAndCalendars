package it.unibz.taskcalendarservice.calendar.application.event;

import lombok.Getter;
import lombok.Setter;

public abstract class CalendarEvent {
    @Setter
    @Getter
    protected String type;

    @Setter
    @Getter
    protected Object payload;
}
