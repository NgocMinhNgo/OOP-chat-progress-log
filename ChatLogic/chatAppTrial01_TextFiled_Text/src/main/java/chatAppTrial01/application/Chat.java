package chatAppTrial01.application;

import chatAppTrial01.api.CallGeminiAPI;
import chatAppTrial01.entity.Conversation;
import chatAppTrial01.entity.Message;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;

public class Chat {
    @FXML
    private TextArea chatArea;
    @FXML
    private TextField inputField;
    @FXML
    private Button sendButton;

    private Conversation conversation = new Conversation();
    private CallGeminiAPI geminiAPI = new CallGeminiAPI();

    @FXML
    public void initialize() {
        sendButton.setOnAction(event -> sendMessage());
        inputField.setOnAction(event -> sendMessage());
    }

    private void sendMessage() {
        String userInput = inputField.getText().trim();
        if (!userInput.isEmpty()) {
            // Thêm tin nhắn người dùng vào conversation
            // Tạo 1 object message
            Message userMessage = new Message("user", userInput);
            conversation.addMessage(userMessage);

            // Hiển thị tin nhắn người dùng
            // Gán message vào chat box

            chatArea.appendText("You: " + userInput + "\n\n");
            inputField.clear();

            // Gọi API Gemini trong một luồng riêng để không block UI
            // Xử lý String input người dùng
            new Thread(() -> {
                try {
                    //String response = geminiAPI.generateContent(conversation.getMessages());
                    String response = geminiAPI.generateContent(userMessage.getContent());

                    // Thêm phản hồi vào conversation
                    Message modelMessage = new Message("model", response);
                    conversation.addMessage(modelMessage);

                    // Hiển thị phản hồi trong UI thread
                    javafx.application.Platform.runLater(() -> {
                        chatArea.appendText("Gemini: " + response + "\n\n");
                    });

                } catch (IOException e) {
                    javafx.application.Platform.runLater(() -> {
                        chatArea.appendText("Error: " + e.getMessage() + "\n\n");
                    });
                }
            }).start();
        }
    }
}