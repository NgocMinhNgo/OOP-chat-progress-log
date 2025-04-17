package chatAppTrial01.entity;

public class UserMessage extends Message {
    public UserMessage(String textContent) {
        super("user", textContent);
    }
}