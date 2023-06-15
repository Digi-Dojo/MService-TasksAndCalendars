package it.unibz.taskcalendarservice.calendar.application.event;

import it.unibz.taskcalendarservice.calendar.domain.CalendarEvent;
import lombok.Getter;

public class CalendarEventUpdated {
    private static final String EVENT_UPDATED = "Event updated";

    @Getter
    private String type = EVENT_UPDATED ;

    @Getter
    private CalendarEvent payload;

    public CalendarEventUpdated(CalendarEvent payload){this.payload = payload;}

    public String toJson(){
        return "{" +
                "\"type\": \"" + type + "\"," +
                "\"payload\": {" +
                "\"id\": \"" + payload.getId() + "\"," +
                "\"title\": \"" + payload.getTitle() + "\"," +
                "\"description\": \"" + payload.getDescription() + "\"," +
                "\"startTime\": \"" + payload.getStartDate() + "\"" +
                "\"endTime\": \"" + payload.getEndDate() + "\"" +
                "\"tags\": \"" + payload.getTags() + "\"" +
                "}" +
                "}";
    }
}
