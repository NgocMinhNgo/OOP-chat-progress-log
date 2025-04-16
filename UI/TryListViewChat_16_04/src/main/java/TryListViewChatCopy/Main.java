package TryListViewChatCopy;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/TryListViewChatCopy/application/sample.fxml"));
        System.out.println("FXML path: " + getClass().getResource("/TryListViewChatCopy/application/sample.fxml"));
        primaryStage.setTitle("Phone List Viewer");
        primaryStage.setScene(new Scene(root, 300, 400));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}