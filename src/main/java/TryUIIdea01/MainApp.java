package TryUIIdea01;

import TryUIIdea01.application.MainAppController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {
    @Override
    public void start(Stage stage) {
        MainAppController mainController = new MainAppController();

        Scene scene = new Scene(mainController.getView(), 1200, 800);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}