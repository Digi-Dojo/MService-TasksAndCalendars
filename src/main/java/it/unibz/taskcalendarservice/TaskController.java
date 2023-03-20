package it.unibz.taskcalendarservice;

import org.springframework.beans.factory.annotation.Value;

import java.sql.*;
import java.util.List;
import java.util.Optional;

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

    public TaskController(String description, Task.Status taskStatus, User relatedUser, Place relatedPlace, List<String> tags){
        this.description = description;
        this.taskStatus = taskStatus;
        this.relatedUser = relatedUser;
        this.relatedPlace = relatedPlace;
        this.tags = tags;
    }

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

    //CREATE OPERATION:
    /** Creates a task entry in the database associated to this instance of a task
     * @thrown SQLException
     */
    public void createCalendarEvent() throws SQLException {
        connectToDB();

        assert conn != null;
        Long userId = relatedUser.id();
        Statement stmt = conn.createStatement();
        QueryBuilder qb = new QueryBuilder();
        String sql = qb.insert("task_calendar_db.calendar_events")
                .value("description", description)
                .value("status", String.valueOf(taskStatus))
                .value("user_id", userId.toString())
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
                .from("task_calendar_db.tasks")
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
                .from("task_calendar_db.tasks")
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