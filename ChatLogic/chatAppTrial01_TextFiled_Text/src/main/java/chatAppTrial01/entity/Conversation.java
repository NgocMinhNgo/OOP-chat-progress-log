package chatAppTrial01.entity;

import java.util.ArrayList;
import java.util.List;

public class Conversation {
    private List<Message> messages;

    public Conversation() {
        this.messages = new ArrayList<>();
    }

    public void addMessage(Message message) {
        messages.add(message);
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void clear() {
        messages.clear();
    }
}