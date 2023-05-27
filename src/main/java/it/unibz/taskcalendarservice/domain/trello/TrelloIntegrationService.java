package it.unibz.taskcalendarservice.domain.trello;

import com.julienvey.trello.Trello;
import com.julienvey.trello.domain.Card;
import com.julienvey.trello.domain.TList;
import com.julienvey.trello.impl.TrelloImpl;
import it.unibz.taskcalendarservice.application.calendar.CalendarEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TrelloIntegrationService {

    private final Trello trelloClient;
    private final String trelloBoardId;
    private final String trelloListId;

    public TrelloIntegrationService(
            @Value("${trello.api.key}") String trelloApiKey,
            @Value("${trello.api.token}") String trelloApiToken,
            @Value("${trello.board.id}") String trelloBoardId,
            @Value("${trello.list.id}") String trelloListId
    ) {
        this.trelloClient = new TrelloImpl(trelloApiKey, trelloApiToken);
        this.trelloBoardId = trelloBoardId;
        this.trelloListId = trelloListId;
    }

    public void createTrelloCardFromEvent(CalendarEvent event) {
        Card card = new Card();
        card.setName(event.getTitle());
        // Set other card properties based on the event, e.g., description, due date, labels, etc.

        TList list = trelloClient.getList(trelloListId);
        card = list.createCard(card);
    }

    public void updateTrelloCard(CalendarEvent event) {
        String cardId = event.getTrelloCardId(); // Get the Trello card ID associated with the event

        Card card = trelloClient.getCard(cardId);
        if (card != null) {
            card.setName(event.getTitle());
            // Update other card properties based on the modified event details, e.g., description, due date, labels, etc.

            card.update();
        }
    }


    // I still need other methods for updating, retrieving, or syncing calendar events with Trello
}
