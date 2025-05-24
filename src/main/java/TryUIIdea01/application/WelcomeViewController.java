package TryUIIdea01.application;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

// WelcomeViewController.java
public class WelcomeViewController {
    private Runnable onStartNewConversation; // Functional interface để gọi lại

    // Thiết lập callback
    public void setOnStartNewConversation(Runnable callback) {
        this.onStartNewConversation = callback;
    }

    @FXML
    private void showProductSelectionView(ActionEvent event) {
        if (onStartNewConversation != null) {
            onStartNewConversation.run();
        }
    }
}

//public class WelcomeViewController implements Initializable {
//
//    @FXML private Button selectProductButton;
//
//    private Runnable onStartNewConversation;
//
//    @FXML
//    private Button startButton;
//
//    @Override
//    public void initialize(URL location, ResourceBundle resources) {
//        // Có thể thêm các thiết lập khởi tạo nếu cần
//    }
//
//    @FXML
//    private void showProductSelectionView(ActionEvent event) {
//        if (onStartNewConversation != null) {
//            onStartNewConversation.run();
//        }
//    }
//
//    public void setOnStartNewConversation(Runnable handler) {
//        this.onStartNewConversation = handler;
//    }
//}