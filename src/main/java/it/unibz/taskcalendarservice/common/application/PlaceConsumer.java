package it.unibz.taskcalendarservice.common.application;

import it.unibz.taskcalendarservice.common.domain.Place;
import org.json.JSONObject;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PlaceConsumer {

    private List<Place> placeList = new ArrayList<>();
    //TODO: add to the database
    @KafkaListener(topics = "place.created", groupId = "03")
    public void consumePlaceCreatedEvent(String jsonMessage) {
        try {
            JSONObject jsonObject = new JSONObject(jsonMessage);
            JSONObject payload = jsonObject.getJSONObject("payload");

            String idAsString = payload.getString("id");
            Long placeId = Long.parseLong(idAsString);
            String placeName = payload.getString("name");

            Place place = new Place(placeId, placeName);
            //list of created Places
            placeList.add(place);
            System.out.println(placeName);
        } catch (Exception e) {
            System.err.println("Error processing places created event: " + e.getMessage());
        }
    }

    @KafkaListener(topics = "place.deleted", groupId = "04")
    public void consumePlaceUpdatedEvent(String jsonMessage) {
        try {
            JSONObject jsonObject = new JSONObject(jsonMessage);
            JSONObject payload = jsonObject.getJSONObject("payload");

            Long placeId = payload.getLong("id");

            placeList.remove(placeId);

        } catch (Exception e) {
            System.err.println("Error processing places created event: " + e.getMessage());
        }
    }
    public List<Place> getPlaceList() {
        return placeList;
    }
}
