package TryListViewChatCopy.entity;

public class UserMessage extends Message {
    public UserMessage(String textContent) {
        super("user", textContent);
    }
}