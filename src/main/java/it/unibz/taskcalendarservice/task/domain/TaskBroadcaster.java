package it.unibz.taskcalendarservice.task.domain;

public interface TaskBroadcaster {
    void emitTaskCreated(Task task);
    void emitTaskUpdated(Task task);
}
