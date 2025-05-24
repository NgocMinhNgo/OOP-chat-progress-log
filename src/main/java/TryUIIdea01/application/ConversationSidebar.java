package TryUIIdea01.application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid;

import java.util.function.Consumer;
import java.util.stream.Collectors;

public class ConversationSidebar extends VBox {
    private final ListView<ConversationItem> conversationListView;
    private final ObservableList<Conversation> conversations;
    private final Consumer<Conversation> onConversationSelected;

    public ConversationSidebar(ObservableList<Conversation> conversations,
                               Consumer<Conversation> selectionHandler) {
        this.conversations = conversations;
        this.onConversationSelected = selectionHandler;

        // 1. Tạo header với nút New Chat
        HBox header = createHeader();

        // 2. Tạo ListView hiển thị các conversation
        conversationListView = new ListView<>();
        conversationListView.setCellFactory(param -> new ConversationCell());
        conversationListView.setItems(createConversationItems());

        // 3. Thiết lập layout
        this.getChildren().addAll(header, conversationListView);
        this.setSpacing(10);
        this.setPrefWidth(250);
        this.setStyle("-fx-background-color: #f0f4f8; -fx-padding: 10;");
    }

    private HBox createHeader() {
        Button newChatButton = new Button("New Chat");
        newChatButton.setGraphic(new FontIcon(FontAwesomeSolid.PLUS));
        newChatButton.setStyle("-fx-background-color: #4a90e2; -fx-text-fill: white;");
        newChatButton.setOnAction(e -> onConversationSelected.accept(null));

        HBox header = new HBox(newChatButton);
        header.setAlignment(Pos.CENTER_LEFT);
        header.setPadding(new Insets(5, 0, 15, 0));
        return header;
    }

    private ObservableList<ConversationItem> createConversationItems() {
        return FXCollections.observableArrayList(
                conversations.stream()
                        .map(conv -> new ConversationItem(conv))
                        .collect(Collectors.toList())
        );
    }

    // Cập nhật khi có conversation mới
    public void addConversation(Conversation conversation) {
        conversationListView.getItems().add(new ConversationItem(conversation));
    }
}