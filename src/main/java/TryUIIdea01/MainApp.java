package TryUIIdea01;

import TryUIIdea01.application.MainAppController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
//
//public class MainApp extends Application {
//    @Override
//    public void start(Stage stage) {
//        MainAppController mainController = new MainAppController();
//
//        Scene scene = new Scene(mainController.getView(), 1200, 800);
//        stage.setScene(scene);
//        stage.show();
//    }
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//}

public class MainApp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/TryUIIdea01/application/MainAppView.fxml"));
        BorderPane root = loader.load();

        Scene scene = new Scene(root, 1200, 800);
        scene.getStylesheets().add(getClass().getResource("/TryUIIdea01/application/styles.css").toExternalForm());

        primaryStage.setTitle("Product Advisor");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}