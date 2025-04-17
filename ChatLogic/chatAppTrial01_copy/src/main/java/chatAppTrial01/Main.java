package chatAppTrial01;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/chatAppTrial01/application/chat.fxml"));
            System.out.println("FXML path: " + getClass().getResource("/chatAppTrial01/application/chat.fxml"));
            Parent root = loader.load();

            primaryStage.setTitle("Gemini Chat App");
            primaryStage.setScene(new Scene(root, 800, 600));
            primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}