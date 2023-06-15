package it.unibz.taskcalendarservice.calendar.application.event;
import it.unibz.taskcalendarservice.calendar.domain.CalendarEvent;
import lombok.Getter;

public class CalendarEventDeleted extends CalendarEvent {

    private static final String EVENT_DELETED = "Event deleted";

    @Getter
    private String type = EVENT_DELETED ;

    @Getter
    private CalendarEvent payload;

    public CalendarEventDeleted(CalendarEvent payload){this.payload = payload;}

    public String toJson(){
        return "{" +
                "\"type\": \"" + type + "\"," +
                "\"payload\": {" +
                "\"id\": \"" + payload.getId() + "\"," +
                "}" +
                "}";
    }
}