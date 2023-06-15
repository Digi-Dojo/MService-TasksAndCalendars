package it.unibz.taskcalendarservice.task.application.kafka;

import it.unibz.taskcalendarservice.task.application.event.TaskCreated;
import it.unibz.taskcalendarservice.task.application.event.TaskUpdated;
import it.unibz.taskcalendarservice.task.domain.Task;
import it.unibz.taskcalendarservice.task.domain.TaskBroadcaster;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class TaskProducer implements TaskBroadcaster {

    @Autowired
    private final KafkaTemplate<String, String> startupKafkaTemplate;

    @Override
    public void emitTaskCreated(Task task) {
        TaskCreated taskCreated = new TaskCreated(task);
        startupKafkaTemplate.send("task.created", taskCreated.toJson());
    }

    @Override
    public void emitTaskUpdated(Task task) {
        TaskUpdated calendarEventUpdateEvent = new TaskUpdated(task);
        startupKafkaTemplate.send("task.updated", calendarEventUpdateEvent.toJson());
    }
}