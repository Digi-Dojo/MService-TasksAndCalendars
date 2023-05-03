package it.unibz.taskcalendarservice.domain.task;

import it.unibz.taskcalendarservice.QueryBuilder;
import it.unibz.taskcalendarservice.application.Place;
import it.unibz.taskcalendarservice.application.User;
import it.unibz.taskcalendarservice.application.task.Task;
import it.unibz.taskcalendarservice.application.task.Status;


import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;

import java.sql.*;
import java.util.List;
import java.util.Optional;
@Controller
public class TaskController {
//     This class should handle HTTP requests related to Tasks, including creating, updating,
//     deleting, and retrieving Task entities. It should delegate to the TaskService to perform business logic
    private String dbUrl;
    private String dbUsername;
    private String dbPassword;
    private String dbDriver;

    private Connection conn;
    private String description;
    private Status status;
    private User user;
    private Place place;
    private List<String> tags;

    public TaskController(String description, Status taskStatus, User relatedUser, Place relatedPlace, List<String> tags){}
    public TaskController(){    }

     public TaskController(String description, Status status) {
        this.description = description;
        this.status = status;
    }
    public TaskController(String description, Status status, Place place) {
        this.description = description;
        this.status = status;
        this.place= place;
    }
    public TaskController(String description, Status status, User user) {
        this.description = description;
        this.status = status;
        this.user = user;
    }
    public TaskController(String description, Status status, List<String> tags) {
        this.description = description;
        this.status = status;
        this.tags = tags;
    }
    public TaskController(String description, Status status,  User user, List<String> tags) {
        this.description = description;
        this.status = status;
        this.user = user;
        this.tags = tags;
    }
    public TaskController(String description, Status status, Place place, List<String> tags) {
        this.description = description;
        this.status = status;
        this.place = place;
        this.tags = tags;
    }
     // -- ending of new constructors

    //Connection and disconnection methods for the DB
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

    public void createTask(String description, Status taskStatus, Place relatedPlace, List<String> tags) throws SQLException {
        connectToDB();

        assert conn != null;
        Statement stmt = conn.createStatement();
        QueryBuilder qb = new QueryBuilder();
        String sql = qb.insert("task_calendar_db.task")
                .value("description", description)
                .value("status", String.valueOf(taskStatus))
                .value("place_id", String.valueOf(relatedPlace))
                .buildInsert();
        try {
            stmt.executeQuery(sql);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        closeConnection();
    }

    public void createTask(String description, Status status) throws SQLException {
        connectToDB();

        assert conn != null;
        Long userId = user.id();
        Statement stmt = conn.createStatement();
        QueryBuilder qb = new QueryBuilder();
        String sql = qb.insert("task_calendar_db.task")
                .value("description", description)
                .value("status", String.valueOf(status))
                .buildInsert();
        try {
            stmt.executeQuery(sql);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        closeConnection();
    }

    public void createTask(String description, Status status, Place place) throws SQLException {
        connectToDB();

        assert conn != null;
        Statement stmt = conn.createStatement();
        QueryBuilder qb = new QueryBuilder();
        String sql = qb.insert("task_calendar_db.task")
                .value("description", description)
                .value("status", String.valueOf(status))
                .value("place_id", String.valueOf(place))
                .buildInsert();
        try {
            stmt.executeQuery(sql);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        closeConnection();
    }

    public void createTask(String description, Status status, List<String> tags) throws SQLException {
        connectToDB();

        assert conn != null;
        Statement stmt = conn.createStatement();
        QueryBuilder qb = new QueryBuilder();
        String sql = qb.insert("task_calendar_db.task")
                .value("description", description)
                .value("status", String.valueOf(status))
                .buildInsert();

        // Add tags to the query
        for (String tag : tags) {
            sql += qb.insert("task_calendar_db.task")
                    .value("event_id", "?")
                    .value("tag_name", "?")
                    .buildInsert();
        }
        try {
            stmt.executeQuery(sql);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        closeConnection();
    }

    //READ OPERATIONS:
    /** Returns all tasks associated to the user in the database
     * @thrown SQLException
     */
    public ResultSet getTasks() throws SQLException {
        connectToDB();

        assert conn != null;
        Long userId = user.id();
        Statement stmt = conn.createStatement();
        QueryBuilder qb = new QueryBuilder();
        String sql = qb.select("*")
                .from("task_calendar_db.task")
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

    /** Returns a task in the database
     * @param taskId the id of the task to be returned
     * @thrown SQLException
     */
    public ResultSet getTaskByID(int taskId) throws SQLException {
        connectToDB();

        assert conn != null;
        Statement stmt = conn.createStatement();
        QueryBuilder qb = new QueryBuilder();
        String sql = qb.select("*")
                .from("task_calendar_db.task")
                .where("id = " + taskId)
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

    //UPDATE OPERATIONS:
    /**Updates a task in the database
     * @param taskId the id of the task to be updated
     * @thrown SQLException
     */
    public void updateTask(int taskId) throws SQLException {
        connectToDB();

        assert conn != null;
        Long userId = user.id();
        Statement stmt = conn.createStatement();
        QueryBuilder qb = new QueryBuilder();
        QueryBuilder sql = qb.update("task_calendar_db.tasks ta")
                .join("task_calendar_db.tasks_tags tat", "ta.id = tat.tasks_id", Optional.empty())
                .set("ta.description = " + description)
                .set("ta.status = " + status)
                .set("ta.place_id = " + place)
                .set("ta.user_id = " + userId);
        for (int i = 0; i < tags.size(); i++) {
            sql = sql.set("tat.tag_id = " + tags.get(i));
        }
        String finalSql = sql.where("ta.id = " + taskId)
                .buildUpdate();
        try {
            stmt.executeUpdate(finalSql);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection();
    }

    //DELETE OPERATIONS:
    /**Deletes a task in the database
     * @param taskId the id of the task to be deleted
     * @thrown SQLException
     */
    public void deleteCalendarEvent(int taskId) throws SQLException {
        connectToDB();

        assert conn != null;
        Statement stmt = conn.createStatement();
        QueryBuilder qb = new QueryBuilder();
        String sql = qb.delete("task_calendar_db.tasks")
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