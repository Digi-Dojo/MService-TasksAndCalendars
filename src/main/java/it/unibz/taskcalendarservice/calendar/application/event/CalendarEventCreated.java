package it.unibz.taskcalendarservice.calendar.application.event;
import lombok.Getter;

public class CalendarEventCreated extends CalendarEvent {

    private static final String EVENT_CREATED = "Event created";

    @Getter
    private String type = EVENT_CREATED ;

    @Getter
    private it.unibz.taskcalendarservice.calendar.domain.CalendarEvent payload;

    public CalendarEventCreated(it.unibz.taskcalendarservice.calendar.domain.CalendarEvent payload){this.payload = payload;}

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
                "\"user\": \"" + payload.getUser() + "\"" +
                "}" +
                "}";
    }
}