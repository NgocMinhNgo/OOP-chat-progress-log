package TryUIIdea01.application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ConversationCell extends ListCell<ConversationItem> {
    private final HBox root = new HBox();
    private final Label titleLabel = new Label();
    private final Label previewLabel = new Label();
    private final Label timeLabel = new Label();

    public ConversationCell() {
        // 1. Thiết lập layout
        VBox textBox = new VBox(titleLabel, previewLabel);
        textBox.setSpacing(3);

        root.getChildren().addAll(textBox, timeLabel);
        root.setSpacing(10);
        root.setPadding(new Insets(10));
        root.setAlignment(Pos.CENTER_LEFT);

        // 2. Thiết lập style
        titleLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 14;");
        previewLabel.setStyle("-fx-text-fill: #666; -fx-font-size: 12;");
        timeLabel.setStyle("-fx-text-fill: #999; -fx-font-size: 11;");

        // 3. Hiệu ứng hover
        this.hoverProperty().addListener((obs, oldVal, isHovering) -> {
            if (isHovering) {
                root.setStyle("-fx-background-color: #e3eaf3; -fx-background-radius: 5;");
            } else {
                root.setStyle("-fx-background-color: transparent;");
            }
        });
    }

    @Override
    protected void updateItem(ConversationItem item, boolean empty) {
        super.updateItem(item, empty);

        if (empty || item == null) {
            setGraphic(null);
        } else {
            titleLabel.setText(item.getTitle());
            previewLabel.setText(item.getPreview());
            timeLabel.setText(item.getTimeString());
            setGraphic(root);
        }
    }
}