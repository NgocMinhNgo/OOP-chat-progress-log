package TryUIIdea01.entity;

import java.time.Instant;

public abstract class Message {
    private final String role;
    private final String textContent;
    private final Instant timestamp;

    public Message(String role, String textContent) {
        this.role = role;
        this.textContent = textContent;
        this.timestamp = Instant.now();
    }

    // Getters
    public String getRole() { return role; }
    public String getTextContent() { return textContent; }
    public Instant getTimestamp() { return timestamp; }
}
