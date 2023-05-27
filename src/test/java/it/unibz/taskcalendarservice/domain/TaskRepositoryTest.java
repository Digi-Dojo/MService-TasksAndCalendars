package it.unibz.taskcalendarservice.domain;

import it.unibz.taskcalendarservice.application.task.Status;
import it.unibz.taskcalendarservice.application.task.Task;
import it.unibz.taskcalendarservice.domain.task.TaskRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class TaskRepositoryTest {

    @Autowired
    private TaskRepository taskRepository;

    @Test
    public void testCreateTaskWithId() {
        // Create a new task with an ID
        Long taskId = 5869473925487263L;
        String description = "Task description";
        Status status = Status.DONE;

        Task task = new Task(taskId, description, status, Optional.empty(), Optional.empty(), Optional.empty());

        // Save the task
        Task savedTask = taskRepository.save(task);

        // Verify that the task is saved successfully with the provided ID
        assertNotNull(savedTask);
        assertEquals(taskId, savedTask.getId());
        assertEquals(description, savedTask.getDescription());
        assertEquals(status, savedTask.getStatus());
    }
}

