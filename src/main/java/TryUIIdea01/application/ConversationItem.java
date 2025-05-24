package TryUIIdea01.application;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ConversationItem {
    private final Conversation conversation;
    private final LocalDateTime createdAt;

    public ConversationItem(Conversation conversation) {
        this.conversation = conversation;
        this.createdAt = LocalDateTime.now();
    }

    public String getTitle() {
        return conversation.getProductType() + " - " + conversation.getAnalysisMethod();
    }

    public String getPreview() {
        if (conversation.getMessages().isEmpty()) {
            return "New conversation";
        }
        return conversation.getMessages().get(0).getTextContent();
    }

    public String getTimeString() {
        return createdAt.format(DateTimeFormatter.ofPattern("HH:mm"));
    }

    public Conversation getConversation() {
        return conversation;
    }
}