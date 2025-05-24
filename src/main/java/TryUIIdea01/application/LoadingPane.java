package TryUIIdea01.application;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

//public class TryUIIdea01.application.LoadingPane extends StackPane {
//    public TryUIIdea01.application.LoadingPane(String productType) {
//        BlurEffect blur = new BlurEffect(10, 10);
//        this.setEffect(blur);
//
//        VBox content = new VBox(10);
//        content.setAlignment(Pos.CENTER);
//
//        ProgressIndicator spinner = new ProgressIndicator();
//        Label message = new Label("Scraping fresh " + productType + " data for you, please wait a bit!");
//
//        content.getChildren().addAll(spinner, message);
//        this.getChildren().add(content);
//    }
//}

public class LoadingPane extends StackPane {
    //public TryUIIdea01.application.LoadingPane(String productType) {
    public LoadingPane() {

        GaussianBlur blur = new GaussianBlur(10);
        this.setEffect(blur);

        VBox content = new VBox(10);
        content.setAlignment(Pos.CENTER);

        ProgressIndicator spinner = new ProgressIndicator();
        Label message = new Label("Scraping fresh data for you, please wait a bit!");

        content.getChildren().addAll(spinner, message);
        this.getChildren().add(content);
    }
}