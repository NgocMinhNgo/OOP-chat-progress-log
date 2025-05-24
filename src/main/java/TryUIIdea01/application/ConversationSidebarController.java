package TryUIIdea01.application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class ConversationSidebarController implements Initializable {
    @FXML private ListView<ConversationItem> conversationListView;
    @FXML private Button newChatButton;
    @FXML private HBox header;

    private ObservableList<Conversation> conversations;
    private Consumer<Conversation> onConversationSelected;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Thiết lập cơ bản có thể thêm ở đây nếu cần
    }

    public void setup(ObservableList<Conversation> conversations,
                      Consumer<Conversation> selectionHandler) {
        this.conversations = conversations;
        this.onConversationSelected = selectionHandler;

        // Cấu hình button
        newChatButton.setOnAction(e -> onConversationSelected.accept(null));

        // Cấu hình ListView
        conversationListView.setCellFactory(param -> new ConversationCell());
        conversationListView.setItems(createConversationItems());
    }

    private ObservableList<ConversationItem> createConversationItems() {
        return FXCollections.observableArrayList(
                conversations.stream()
                        .map(ConversationItem::new)
                        .collect(Collectors.toList())
        );
    }

    public void addConversation(Conversation conversation) {
        conversationListView.getItems().add(new ConversationItem(conversation));
    }
}