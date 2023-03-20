package it.unibz.taskcalendarservice;
import org.springframework.beans.factory.annotation.Value;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;

public class TaskController {
//     This class should handle HTTP requests related to Tasks, including creating, updating,
//     deleting, and retrieving Task entities. It should delegate to the TaskService to perform business logic

    public class CalendarEventController {
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
        private Task.Status status;
        private User user;
        private Place place;
        private List<String> tags;

        public CalendarEventController(String description, Task.Status status, User user, Place place, List<String> tags) {
            this.description = description;
            this.status = status;
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

        public ResultSet getTask() throws SQLException {
            connectToDB();
            assert conn != null;
            Long userId = user.id();
            Statement stmt = conn.createStatement();
            QueryBuilder qb = new QueryBuilder();
            String sql = qb.select("*")
                    .from("task_calendar_db.tasks")
                    .where("user_id = " + userId)
                    .buildSelect();
            ResultSet rs = null;
            try {
                rs = stmt.executeQuery(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            closeConnection();
            return rs;
        }

    }
}

