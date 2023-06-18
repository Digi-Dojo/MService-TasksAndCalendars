package it.unibz.taskcalendarservice.common.application;

import it.unibz.taskcalendarservice.common.domain.User;
import org.json.JSONObject;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserConsumer {
    private List<User> userList = new ArrayList<>();
    //TODO: add to the database
    @KafkaListener(topics = "user.created", groupId = "00")
    public void consumeUserCreatedEvent(String jsonMessage) {
        try {
            JSONObject jsonObject = new JSONObject(jsonMessage);
            JSONObject payload = jsonObject.getJSONObject("payload");

            String idAsString = payload.getString("id");
            Long userId = Long.parseLong(idAsString);
            String userName = payload.getString("name");

            User user = new User(userId, userName);
            //list of created User
            userList.add(user);
            System.out.println(userName);
        } catch (Exception e) {
            System.err.println("Error processing user created event: " + e.getMessage());
        }
    }

    @KafkaListener(topics = "user.deleted", groupId = "01")
    public void consumeUserUpdatedEvent(String jsonMessage) {
        try {
            JSONObject jsonObject = new JSONObject(jsonMessage);
            JSONObject payload = jsonObject.getJSONObject("payload");

            Long userId = payload.getLong("id");

            userList.remove(userId);

        } catch (Exception e) {
            System.err.println("Error processing user created event: " + e.getMessage());
        }
    }

    public List<User> getUserList() {
        return userList;
    }
}
