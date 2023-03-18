package it.unibz.taskcalendarservice;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;

public class CalendarEventController{
//    This class should handle HTTP requests related to CalendarEvents, including creating, updating,
//    deleting, and retrieving CalendarEvent entities. It should delegate to the CalendarEventService to perform business logic.

    @Value("${spring.datasource.url}")
    private String dbUrl;
    @Value("${spring.datasource.username}")
    private String dbUsername;
    @Value("${spring.datasource.password}")
    private String dbPassword;
    @Value("$(spring.datasource.driver-class-name)")
    private String dbDriver;

    private String description;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private User user;
    private Place place;
    private List<String> tags;// same as on the task (array is better)

    public CalendarEventController(String description, LocalDateTime startDate, LocalDateTime endDate, User user, Place place, List<String> tags) {
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.user = user;
        this.place = place;
        this.tags = tags;
    }

    public ResultSet getCalendarEvent() throws SQLException {
        // TODO: get the CalendarEvent with the given id from the database task_calendar_db
        Connection conn = null;
        try {
            Class.forName(dbDriver);
            conn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the connection
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        assert conn != null;
        Long userId = user.id();
        Statement stmt = conn.createStatement();
        String sql =    "SELECT * " +
                        "FROM task_calendar_db.calendar_events " +
                        "WHERE start_date like '%" + startDate+ "%'" +
                        "AND user_id = "+ userId;
        ResultSet rs = stmt.executeQuery(sql);
        return rs;
    }

    public void getCalendarDescription() {
        return;
    }

    public void getCalendarStartDate() {
        return;
    }

    public void getCalendarEndDate() {
        return;
    }

    public void getCalendarUser() {
        return;
    }

    public void getCalendarPlace() {
        return;
    }

    public void getCalendarTags() {
        return;
    }

    public void setCalendarEvent() {
        return;
    }

    public void setCalendarDescription() {
        return;
    }

    public void setCalendarStartDate() {
        return;
    }

    public void setCalendarEndDate() {
        return;
    }

    public void setCalendarUser() {
        return;
    }

    public void setCalendarPlace() {
        return;
    }

    public void setCalendarTags() {
        return;
    }

    public void createCalendarEvent() {
        return;
    }

    public void updateCalendarEvent() {
        return;
    }

    public void deleteCalendarEvent() {
        return;
    }

    public void retrieveCalendarEvent() {
        return;
    }

}
