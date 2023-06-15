package it.unibz.taskcalendarservice.common.application;

import org.json.JSONObject;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserConsumer {
    private List<User> userList = new ArrayList<>();

    @KafkaListener(topics = "user.create", groupId = "${it.unibz.archlab.digidojo.engagement.kafka.consumer.group_id}")
    public void consumeUserCreatedEvent(String jsonMessage) {
        try {
            JSONObject jsonObject = new JSONObject(jsonMessage);
            JSONObject payload = jsonObject.getJSONObject("payload");

            Long userId = payload.getLong("id");
            String userName = payload.getString("name");

            User user = new User(userId, userName);
            userList.add(user);

            System.out.println("User created: ID=" + userId + ", Name=" + userName);

            // Additional processing or handling of the user created event
        } catch (Exception e) {
            System.err.println("Error processing user created event: " + e.getMessage());
        }
    }
}
