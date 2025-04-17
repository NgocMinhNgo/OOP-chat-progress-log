package chatAppTrial01.application;

import chatAppTrial01.api.CallGeminiAPI;
import chatAppTrial01.entity.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.List;

public class Chat {
//    @FXML
//    private TextArea chatArea;
    @FXML
    private ListView<Message> messageListView;
    private ObservableList<Message> messageData = FXCollections.observableArrayList();

    @FXML
    private TextField inputField;
    @FXML
    private Button sendButton;

    private Conversation conversation = new Conversation();
    private CallGeminiAPI geminiAPI = new CallGeminiAPI();

    @FXML
    public void initialize() {
        sendButton.setOnAction(event -> sendAndShowMessage());
        inputField.setOnAction(event -> sendAndShowMessage());
        //
        messageListView.setItems(messageData);
        messageListView.setCellFactory(param -> new MessageCell());
    }


    // Chú ý: Hiện lên màn hình vẫn là TextField, không phải ListView --> vẫn đang hiện text thông thường
    // Message chưa được configure để chứa list JSON object

    // Cần phải update để có thể show ra ới vì bây h message đã khác,
    // Đây mới đang chỉ là TextField chứ ko phải là ListView
    //1
//    private void updateChatArea(String message) {
//        chatArea.appendText(message + "\n\n");
//    }
    //2
    private void updateChatArea(Message message) {
        //chatArea.appendText(message + "\n\n");
        messageData.add(message);
    }

//    private void sendAndShowMessage() {
//        String userInputText = inputField.getText().trim();
//        if (!userInputText.isEmpty()) {
//            Message userMessage = new Message("user", userInputText);
//            conversation.addMessage(userMessage);
//            updateChatArea("You: " + userMessage.getContent());
//            inputField.clear();
//            handleUserResponse(userMessage);
//        }
//    }

        private void sendAndShowMessage() {
        String userInputText = inputField.getText().trim();
        if (!userInputText.isEmpty()) {
            Message userMsg = new UserMessage(userInputText);
            conversation.addMessage(userMsg);
            // Cho nay la van gan text vao trong Text Area --> ch chuyen thanh listview
            //updateChatArea("You: " + userMsg.getTextContent());
            updateChatArea(userMsg);
            inputField.clear();
            handleUserResponse(userMsg);
        }
    }


    private void handleUserResponse(Message userMessage) {
        new Thread(() -> {
            try {
                //String response = geminiAPI.generateContent(conversation.getMessages());
                // Đây là Gemini trả về text
                //String response = geminiAPI.generateContent(userMessage.getTextContent());
                // Giờ buildthwurr Gemini trả về object list xem naào =))) (bước đn giản)
                // Thật ra Gemini pha trả về file JSON, --> xong mình phải build 1 cái class để chuyển file JSON về Product object
                // Có câ không ta hay có hàm sẵn rồi =)))
                List<Product> listToShow = geminiAPI.generateContent(userMessage.getTextContent());


                //-------------------------------------

                // Thêm phản hồi vào conversation
                //Message modelMessage = new Message("model", response);
                // Hiển thị phản hồi trong UI thread
                Message modelMsg = new ModelMessage("Dưới đây là một số gợi ý", listToShow);
                conversation.addMessage(modelMsg);

                javafx.application.Platform.runLater(() -> {
                    // hay dữ tr dữ đất
                    // cho nay vẫn là hiện text, ko phải ListView
                    //updateChatArea("Gemini: " + modelMsg.getTextContent());
                    updateChatArea(modelMsg);

                });

            } catch (IOException e) {
                javafx.application.Platform.runLater(() -> {
                    System.out.println(e.getMessage());
                    //chatArea.appendText("Error: " + e.getMessage() + "\n\n");
                });
            }
        }).start();
    }

//    private void sendMessage() {
//        String userInput = inputField.getText().trim();
//        if (!userInput.isEmpty()) {
//            // Thêm tin nhắn người dùng vào conversation
//            // Tạo 1 object message
//            Message userMessage = new Message("user", userInput);
//            conversation.addMessage(userMessage);
//
//            // Hiển thị tin nhắn người dùng
//            // Gán message vào chat box
//
//            chatArea.appendText("You: " + userInput + "\n\n");
//            inputField.clear();
//
//            // Gọi API Gemini trong một luồng riêng để không block UI
//            // Xử lý String input người dùng
//            new Thread(() -> {
//                try {
//                    //String response = geminiAPI.generateContent(conversation.getMessages());
//                    String response = geminiAPI.generateContent(userMessage.getContent());
//
//                    // Thêm phản hồi vào conversation
//                    Message modelMessage = new Message("model", response);
//                    conversation.addMessage(modelMessage);
//
//                    // Hiển thị phản hồi trong UI thread
//                    javafx.application.Platform.runLater(() -> {
//                        chatArea.appendText("Gemini: " + response + "\n\n");
//                    });
//
//                } catch (IOException e) {
//                    javafx.application.Platform.runLater(() -> {
//                        chatArea.appendText("Error: " + e.getMessage() + "\n\n");
//                    });
//                }
//            }).start();
//        }
//   }


}