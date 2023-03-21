package it.unibz.taskcalendarservice.domain.task;

import it.unibz.taskcalendarservice.QueryBuilder;
import it.unibz.taskcalendarservice.application.Place;
import it.unibz.taskcalendarservice.application.User;
import it.unibz.taskcalendarservice.application.task.Task;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

import java.sql.*;
import java.util.List;
import java.util.Optional;
@Controller
public class TaskController {
//     This class should handle HTTP requests related to Tasks, including creating, updating,
//     deleting, and retrieving Task entities. It should delegate to the TaskService to perform business logic

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
    private Task.Status taskStatus;
    private User relatedUser;
    private Place relatedPlace;
    private List<String> tags;

    @Autowired
    public TaskController(){    }

     public TaskController(String description, Task.Status taskStatus) {
        this.description = description;
        this.taskStatus = taskStatus;
    }
    public TaskController(String description, Task.Status taskStatus, Place relatedPlace) {
        this.description = description;
        this.taskStatus = taskStatus;
        this.relatedPlace = relatedPlace;
    }
    public TaskController(String description, Task.Status taskStatus, User relatedUser) {
        this.description = description;
        this.taskStatus = taskStatus;
        this.relatedUser = relatedUser;
    }
    public TaskController(String description, Task.Status taskStatus, List<String> tags) {
        this.description = description;
        this.taskStatus = taskStatus;
        this.tags = tags;
    }
    public TaskController(String description, Task.Status taskStatus,  User relatedUser, List<String> tags) {
        this.description = description;
        this.taskStatus = taskStatus;
        this.relatedUser = relatedUser;
        this.tags = tags;
    }
    public TaskController(String description, Task.Status status, Place place, List<String> tags) {
        this.description = description;
        this.taskStatus = taskStatus;
        this.relatedPlace = relatedPlace;
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

    public void createTask(String description, Task.Status taskStatus, Place relatedPlace, List<String> tags) throws SQLException {
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

    public void createTask(String description, Task.Status taskStatus) throws SQLException {
        connectToDB();

        assert conn != null;
        Long userId = relatedUser.id();
        Statement stmt = conn.createStatement();
        QueryBuilder qb = new QueryBuilder();
        String sql = qb.insert("task_calendar_db.task")
                .value("description", description)
                .value("status", String.valueOf(taskStatus))
                .buildInsert();
        try {
            stmt.executeQuery(sql);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        closeConnection();
    }

    public void createTask(String description, Task.Status taskStatus, Place relatedPlace) throws SQLException {
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

    public void createTask(String description, Task.Status taskStatus,List<String> tags) throws SQLException {
        connectToDB();

        assert conn != null;
        Statement stmt = conn.createStatement();
        QueryBuilder qb = new QueryBuilder();
        String sql = qb.insert("task_calendar_db.task")
                .value("description", description)
                .value("status", String.valueOf(taskStatus))
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
        Long userId = relatedUser.id();
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
        Long userId = relatedUser.id();
        Statement stmt = conn.createStatement();
        QueryBuilder qb = new QueryBuilder();
        QueryBuilder sql = qb.update("task_calendar_db.tasks ta")
                .join("task_calendar_db.tasks_tags tat", "ta.id = tat.tasks_id", Optional.empty())
                .set("ta.description = " + description)
                .set("ta.status = " + taskStatus)
                .set("ta.place_id = " + relatedPlace)
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