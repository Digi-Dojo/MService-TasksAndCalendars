package it.unibz.taskcalendarservice.calendar.application.event;
import it.unibz.taskcalendarservice.calendar.domain.CalendarEvent;
import it.unibz.taskcalendarservice.calendar.domain.CalendarEventRepository;
import it.unibz.taskcalendarservice.calendar.domain.SearchCalendarEvent;
import lombok.Getter;

public class CalendarEventCreated extends CalendarEvent {

    private static final String EVENT_CREATED = "Event created";
    private static Long id;

    @Getter
    private String type = EVENT_CREATED ;

    @Getter
    private CalendarEvent payload;


    public CalendarEventCreated(CalendarEvent payload){
        this.payload = payload;

    }

    public String toJson() {
        return "{" +
                "\"type\": \"" + type + "\"," +
                "\"payload\": {" +
                "\"id\": \"" + payload.getId() + "\"," +
                "\"title\": \"" + payload.getTitle() + "\"," +
                "\"description\": \"" + payload.getDescription() + "\"," +
                "\"startTime\": \"" + payload.getStartDate() + "\"," +
                "\"endTime\": \"" + payload.getEndDate() + "\"," +
                "\"tags\": \"" + payload.getTags() + "\"," +
                "\"user\": \"" + payload.getUser() + "\"" +
                "}" +
                "}";
    }
}