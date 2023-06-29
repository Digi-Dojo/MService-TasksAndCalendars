package it.unibz.taskcalendarservice.common.application;

import it.unibz.taskcalendarservice.common.domain.user.CRUDUser;
import org.json.JSONObject;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserConsumer {
    private final CRUDUser crudUser;

    public UserConsumer(CRUDUser crudUser) {
        this.crudUser = crudUser;
    }

    @KafkaListener(topics = "user.created", groupId = "00")
    public void consumeUserCreatedEvent(String jsonMessage) {
        try {
            JSONObject jsonObject = new JSONObject(jsonMessage);
            JSONObject payload = jsonObject.getJSONObject("payload");

            String idAsString = payload.getString("id");
            String userName = payload.getString("name");  // we tested it with event.created as topic and key = title. So it works and should work later on with users!

            crudUser.createUser(userName);
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
            crudUser.deleteUser(userId);

        } catch (Exception e) {
            System.err.println("Error processing user deleted event: " + e.getMessage());
        }
    }


}
