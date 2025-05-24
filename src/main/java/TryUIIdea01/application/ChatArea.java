package TryUIIdea01.application;

import TryUIIdea01.entity.Message;
import TryUIIdea01.entity.MessageCell;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

public class ChatArea extends BorderPane {
    private final ListView<Message> messageListView;
    private final ObservableList<Message> messages;
    private  TextField inputField;
    private  Button sendButton;
    private final ProgressIndicator loadingIndicator;

    public ChatArea() {
        // 1. Khởi tạo vùng hiển thị tin nhắn
        messageListView = new ListView<>();
        messages = FXCollections.observableArrayList();
        messageListView.setItems(messages);
        messageListView.setCellFactory(param -> new MessageCell());

        // 2. Khởi tạo vùng nhập liệu
        HBox inputArea = createInputArea();

        // 3. Loading indicator
        loadingIndicator = new ProgressIndicator();
        loadingIndicator.setVisible(false);
        StackPane.setAlignment(loadingIndicator, Pos.TOP_RIGHT);
        StackPane.setMargin(loadingIndicator, new Insets(10));

        // 4. Thiết lập layout
        StackPane messageContainer = new StackPane(
                createScrollableMessageList(),
                loadingIndicator
        );

        this.setCenter(messageContainer);
        this.setBottom(inputArea);
        this.setStyle("-fx-background-color: #f5f5f5;");
    }

    private ScrollPane createScrollableMessageList() {
        ScrollPane scrollPane = new ScrollPane(messageListView);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        messages.addListener((ListChangeListener<Message>) c -> {
            while (c.next()) {
                if (c.wasAdded()) {
                    Platform.runLater(() -> {
                        messageListView.scrollTo(c.getFrom());
                    });
                }
            }
        });

        return scrollPane;
    }

    private HBox createInputArea() {
        inputField = new TextField();
        inputField.setPromptText("What do you want to buy?");
        inputField.setStyle("-fx-padding: 10; -fx-font-size: 14;");

        sendButton = new Button("Send");
        sendButton.setStyle("-fx-background-color: #4a90e2; -fx-text-fill: white;");
        //sendButton.setGraphic(new FontIcon(FontAwesome.PAPER_PLANE));


        HBox inputBox = new HBox(inputField, sendButton);
        inputBox.setSpacing(10);
        inputBox.setPadding(new Insets(10));
        inputBox.setAlignment(Pos.CENTER);

        return inputBox;
    }

    // ========== Public Methods ==========
    public void addMessage(Message message) {
        messages.add(message);
    }

    public void clearMessages() {
        messages.clear();
    }

    public String getInputText() {
        return inputField.getText();
    }

    public void clearInput() {
        inputField.clear();
    }

    public void setOnSendAction(EventHandler<ActionEvent> handler) {
        sendButton.setOnAction(handler);
        inputField.setOnAction(handler);
    }

    public void setInputDisabled(boolean disabled) {
        inputField.setDisable(disabled);
        sendButton.setDisable(disabled);
    }

    public void showLoading(boolean show) {
        loadingIndicator.setVisible(show);
    }

//    // Có thể thêm phương thức để xử lý tin nhắn đặc biệt
//    public void addProductMessage(String text, List<Product> products) {
//        addMessage(new ProductMessage(text, products));
//    }
}