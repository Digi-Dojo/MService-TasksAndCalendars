package it.unibz.taskcalendarservice.domain.calendar;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import it.unibz.taskcalendarservice.QueryBuilder;
import it.unibz.taskcalendarservice.application.Place;
import it.unibz.taskcalendarservice.application.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

@Controller
public class CalendarEventController{
//    This class should handle HTTP requests related to CalendarEvents, including creating, updating,
//    deleting, and retrieving CalendarEvent entities. It should delegate to the CalendarEventService to perform business logic.

    @Value("${spring.datasource.url}")
    private String dbUrl;
    @Value("${spring.datasource.username}")
    private String dbUsername;
    @Value("${spring.datasource.password}")
    private String dbPassword;
    @Value("${spring.datasource.driver-class-name}")
    private String dbDriver;

    private Connection conn;
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

    private void connectToDB() {
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
    }

    private void closeConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet getCalendarEvent() throws SQLException {
        connectToDB();

        assert conn != null;
        Long userId = user.id();
        Statement stmt = conn.createStatement();
        QueryBuilder qb = new QueryBuilder();
        String sql = qb.select("*")
                        .from("task_calendar_db.calendar_events")
                        .where("user_id = " + userId)
                        .where("start_date like '%" + startDate + "%'")
                        .buildSelect();
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery(sql);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        closeConnection();
        return rs;
    }

    public ResultSet getCalendarDescription(int taskId) throws SQLException {
        connectToDB();

        assert conn != null;
        Long userId = user.id();
        Statement stmt = conn.createStatement();
        QueryBuilder qb = new QueryBuilder();
        String sql  = qb.select("description")
                        .from("task_calendar_db.calendar_events")
                        .where("id = " + taskId)
                        .where("user_id = " + userId)
                        .buildSelect();
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery(sql);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        closeConnection();
        return rs;
    }

    public ResultSet getCalendarStartDate(int taskId) throws SQLException {
        connectToDB();

        assert conn != null;
        Long userId = user.id();
        Statement stmt = conn.createStatement();
        QueryBuilder qb = new QueryBuilder();
        String sql  = qb.select("start_date")
                .from("task_calendar_db.calendar_events")
                .where("id = " + taskId)
                .where("user_id = " + userId)
                .buildSelect();
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery(sql);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        closeConnection();
        return rs;
    }

    public ResultSet getCalendarEndDate(int taskId) throws SQLException{
        connectToDB();

        assert conn != null;
        Long userId = user.id();
        Statement stmt = conn.createStatement();
        QueryBuilder qb = new QueryBuilder();
        String sql  = qb.select("end_date")
                .from("task_calendar_db.calendar_events")
                .where("id = " + taskId)
                .where("user_id = " + userId)
                .buildSelect();
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery(sql);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        closeConnection();
        return rs;
    }

    public ResultSet getCalendarUser() throws SQLException {
        connectToDB();

        assert conn != null;
        Long userId = user.id();
        Statement stmt = conn.createStatement();
        QueryBuilder qb = new QueryBuilder();
        String sql  = qb.select()
                        .from("task_calendar_db.users")
                        .where("user_id = " + userId)
                        .buildSelect();
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery(sql);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        closeConnection();
        return rs;
    }

    public ResultSet getCalendarPlace() throws SQLException {
        connectToDB();

        assert conn != null;
        Long placeId = place.id();
        Statement stmt = conn.createStatement();
        QueryBuilder qb = new QueryBuilder();
        String sql  = qb.select()
                        .from("task_calendar_db.places")
                        .where("id = " + placeId)
                        .buildSelect();
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery(sql);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        closeConnection();
        return rs;
    }

    public ResultSet getCalendarTags(int taskId) throws SQLException {
        connectToDB();

        assert conn != null;
        Long userId = user.id();
        Statement stmt = conn.createStatement();
        QueryBuilder qb = new QueryBuilder();
        String sql  = qb.select("tag_id")
                        .from("task_calendar_db.calendar_events_tags cet")
                        .join("task_calendar_db.calendar_event_tags ce", "cet.calendar_event_id = ce.id", Optional.empty())
                        .where("ce.id = " + taskId)
                        .where("ca.user_id = " + userId)
                        .buildSelect();
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery(sql);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        closeConnection();
        return rs;

    }

    /**
     * Creates a calendar event in the database
     * @throws SQLException
     */
    public void createCalendarEvent() throws SQLException {
        connectToDB();

        assert conn != null;
        Long userId = user.id();
        Statement stmt = conn.createStatement();
        QueryBuilder qb = new QueryBuilder();
        String sql = qb.insert("task_calendar_db.calendar_events")
                        .value("description", description)
                        .value("start_date", startDate.toString())
                        .value("end_date", endDate.toString())
                        .value("user_id", userId.toString())
                        .buildInsert();
        try {
            stmt.executeQuery(sql);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        closeConnection();
    }

    /**
     * Updates a calendar event in the database
     * @param taskId the id of the task to be updated
     * @throws SQLException
     */
    public void updateCalendarEvent(int taskId) throws SQLException {
        connectToDB();

        assert conn != null;
        Long userId = user.id();
        Statement stmt = conn.createStatement();
        QueryBuilder qb = new QueryBuilder();
        QueryBuilder sql = qb.update("task_calendar_db.calendar_events ce")
                            .join("task_calendar_db.calendar_events_tags cet", "ce.id = cet.calendar_event_id", Optional.empty())
                            .set("ce.description = " + description)
                            .set("ce.start_date = " + startDate)
                            .set("ce.end_date = " + endDate)
                            .set("ce.user_id = " + userId);
        for (int i = 0; i < tags.size(); i++) {
            sql = sql.set("cet.tag_id = " + tags.get(i));
        }
        String finalSql = sql.where("ce.id = " + taskId)
                            .buildUpdate();
        try {
            stmt.executeUpdate(finalSql);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection();
    }

    /**
     * Deletes a calendar event from the database
     * @param taskId the id of the task to be deleted
     * @throws SQLException
     */
    public void deleteCalendarEvent(int taskId) throws SQLException {
        connectToDB();

        assert conn != null;
        Long userId = user.id();
        Statement stmt = conn.createStatement();
        QueryBuilder qb = new QueryBuilder();
        String sql = qb.delete("task_calendar_db.calendar_events")
                        .where("id = " + taskId)
                        .buildDelete();
        try {
            stmt.executeQuery(sql);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection();
    }
}
